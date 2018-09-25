package com.blueline.gateway.wechat.tools;

import org.springframework.messaging.converter.SmartMessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 柏霖
 */
public class StompClient {

    ListenableFuture<StompSession> listener;
    WebSocketContainer webSocketContainer;
    String spUrl;
    StompSessionHandlerAdapter connectHandler;
    SmartMessageConverter converter;
    ListenableFutureCallback<StompSession> listenerCallback;


    public StompClient(String spUrl, Map<String, StompFrameHandler> handlerMap) {
        init(spUrl, new ReConnect(), new StringMessageConverter(), handlerMap);
    }

    public StompClient(String spUrl, StompSessionHandlerAdapter connectHandler, Map<String, StompFrameHandler> handlerMap) {
        init(spUrl, connectHandler, new StringMessageConverter(), handlerMap);
    }

    public StompClient(String spUrl, SmartMessageConverter converter, Map<String, StompFrameHandler> handlerMap) {
        init(spUrl, new ReConnect(), new StringMessageConverter(), handlerMap);
    }

    public StompClient(String spUrl, StompSessionHandlerAdapter connectHandler, SmartMessageConverter converter, Map<String, StompFrameHandler> handlerMap) {

        init(spUrl, connectHandler, converter, handlerMap);
    }

    public StompClient(String spUrl, SmartMessageConverter converter, ListenableFutureCallback<StompSession> listenerCallback) {
        init(spUrl, new ReConnect(), converter, listenerCallback);
    }

    public StompClient(String spUrl
            , StompSessionHandlerAdapter connectHandler
            , SmartMessageConverter converter, ListenableFutureCallback<StompSession> listenerCallback
    ) {
        init(spUrl, connectHandler, converter, listenerCallback);


    }

    private void init(String spUrl
            , StompSessionHandlerAdapter connectHandler
            , SmartMessageConverter converter, Map<String, StompFrameHandler> handlerMap
    ) {
        init(spUrl, connectHandler, converter, new ListenableFutureCallback<StompSession>() {

            @Override
            public void onSuccess(StompSession stompSession) {
                Set<Map.Entry<String, StompFrameHandler>> entrySet = handlerMap.entrySet();
                for (Map.Entry<String, StompFrameHandler> entry : entrySet) {
                    stompSession.subscribe(entry.getKey(), entry.getValue());
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void init(String spUrl
            , StompSessionHandlerAdapter connectHandler
            , SmartMessageConverter converter, ListenableFutureCallback<StompSession> listenerCallback
    ) {
        this.spUrl = spUrl;
        this.connectHandler = connectHandler;
        this.converter = converter;
        this.listenerCallback = listenerCallback;

        connect();
    }

    private void connect() {
        List<Transport> transports = new ArrayList<Transport>(1);

        webSocketContainer = ContainerProvider.getWebSocketContainer();
        webSocketContainer.setDefaultMaxTextMessageBufferSize(1 * 1024 * 1024);
        webSocketContainer.setDefaultMaxBinaryMessageBufferSize(1 * 1024 * 1024);

        transports.add(new WebSocketTransport(new StandardWebSocketClient(webSocketContainer)));


        SockJsClient client = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(this.converter);

        stompClient.setReceiptTimeLimit(300);

        stompClient.setDefaultHeartbeat(new long[]{10000L, 10000L});
        ThreadPoolTaskScheduler task = new ThreadPoolTaskScheduler();
        task.initialize();
        stompClient.setTaskScheduler(task);

        listener = stompClient.connect(this.spUrl, this.connectHandler);

        listener.addCallback(this.listenerCallback);
    }

    public void sendSend(String target, Object msg) {

        Object lock = new Object();
        AtomicBoolean end = new AtomicBoolean(false);
        sendSend(target, msg, new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("发送成功");
                    end.set(true);
                    lock.notify();
                }

            }
        }, new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("发送失败");
                    end.set(true);
                    lock.notify();
                }
            }
        });
        synchronized (lock) {
            if (!end.get()) {
                try {
                    System.out.println("等待发送结果");
                    lock.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }

    public void sendSend(String target, Object msg, Runnable callback) {
        sendSend(target, msg, callback, null);
    }

    public void sendSend(String target, Object msg, Runnable callback, Runnable lostCallback) {
        try {
            StompSession session = listener.get();
            session.setAutoReceipt(true);
            StompSession.Receiptable rp = session.send(target, msg);
            rp.addReceiptTask(callback);
            rp.addReceiptLostTask(lostCallback);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        listener.cancel(true);
    }


    class ReConnect extends StompSessionHandlerAdapter {

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            connect();
        }

    }
}
