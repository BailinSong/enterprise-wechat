package com.blueline.gateway.wechat.service

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentSkipListSet
import java.util.concurrent.atomic.AtomicLong
import javax.websocket.*
import javax.websocket.server.ServerEndpoint


@ServerEndpoint(value = "/API/receive")
@Component
class CustomerMessageService {

    companion object {
        //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
        private val onlineCount = AtomicLong(0);
        //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
        private val webSocketSet = ConcurrentSkipListSet<CustomerMessageService>()

        fun publishMessage(message: String) {
            webSocketSet.forEach {
                try {
                    it.sendMessage(message)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }


    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private var session: Session? = null

//    lateinit var pushThread: Thread

    @OnOpen
    fun onOpen(session: Session) {
        println("Current number of people online ${onlineCount.incrementAndGet()}")
        this.session = session
        webSocketSet.add(this)
//        pushThread= thread {
//            while(!pushThread.isInterrupted){
//                try {
//
//                    publishMessage(blockingQueue.take())
//
//                }catch (ex:InterruptedException ){
//
//                }catch (ex :Exception){
//                    ex.printStackTrace()
//                }
//            }
//        }
//        pushThread.start()
    }

    @OnClose
    fun onClose() {
//        pushThread.interrupt()
        webSocketSet.remove(this)  //从set中删除
        println("Current number of people online ${onlineCount.decrementAndGet()}")
    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        /*  log.info("来自客户端的消息:$message")

          //群发消息
          for (item in webSocketSet) {
              try {
                  item.sendMessage(message)
              } catch (e: IOException) {
                  e.printStackTrace()
              }

          }*/
    }

    @OnError
    fun onError(session: Session, error: Throwable) {
        session.close()
        error.printStackTrace()
    }


    fun sendMessage(message: String) {
        this.session!!.getBasicRemote().sendText(message)
    }

}