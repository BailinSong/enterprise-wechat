package com.blueline.gateway.wechat.controller

import com.blueline.gateway.wechat.tools.convertMessage
import com.blueline.gateway.wechat.tools.xml2Map
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = "/API")
class CustomerMessageController {
    @Value("\${ewechat.app.token}")
    var token = ""
    @Value("\${ewechat.app.AESKey}")
    var AESKey = ""



    @Autowired
    private val template: SimpMessagingTemplate? = null

    @RequestMapping(method = [RequestMethod.POST], value = "/app/receiver")
    fun receive(nonce: String?,
                timestamp: String?,
                msg_signature: String?,
                @RequestBody body: String): String {

        println("$nonce $timestamp $msg_signature $body")
        val map = xml2Map(body)
        println(map)
        val data = verify(map["Encrypt"]?.toString() ?: "")
        val event = xml2Map(data)
        println(event)

        template?.convertAndSend("/topic/${event["MsgType"]}_${event["Event"] ?: event["MsgType"]}", event.toString())
        println("/topic/${event["MsgType"]}_${event["Event"] ?: event["MsgType"]}")
        return ""
    }

    @RequestMapping(method = [RequestMethod.GET], value = "/app/receiver")
    fun receivingAddressVerification(nonce: String?,
                                     timestamp: String?,
                                     msg_signature: String?,
                                     echostr: String?): Any {

        val ret = verify(echostr ?: "")
        if (ret.isNotEmpty()) {
            return ret
        }

        println("$nonce $timestamp $msg_signature $echostr")

        return "ok"
    }

    fun verify(echostr: String): String {
        if (echostr.isNullOrBlank().not()) {
           return convertMessage(AESKey, echostr)
        }
        return ""
    }


}