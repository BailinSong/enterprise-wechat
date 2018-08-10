package com.blueline.gateway.wechat.service

import com.blueline.gateway.wechat.domain.AddressBook
import com.blueline.gateway.wechat.domain.CustomApp
import com.blueline.gateway.wechat.domain.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class CustomAppService {



    @Value("\${ewechat.corpid}")
    lateinit var corpID:String
    @Value("\${ewechat.app.secret}")
    lateinit var secret:String
    @Value("\${ewechat.app.agentid}")
    lateinit var agentID: String

    @Autowired
    lateinit var customApp:CustomApp

    @Bean
    fun customApp(): CustomApp {
        return CustomApp(agentID.toInt(),corpID, secret)
    }



    fun sentTextMessage(userID:String,content:String):AppMessageResult{
        return customApp.send(Message(agentid=agentID.toInt(),touser = userID,text = TextContent(content)))
    }
}