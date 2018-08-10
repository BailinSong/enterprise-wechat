package com.blueline.gateway.wechat.domain

import com.blueline.gateway.wechat.domain.model.Message
import com.blueline.gateway.wechat.domain.model.Text
import com.blueline.gateway.wechat.domain.model.TextContent
import org.junit.Test

import org.junit.Assert.*

class CustomAppTest {

    @Test
    fun send() {
        var ca=CustomApp(6,"wxb861157903009f2b","MYwQmLzD0uhik7zjedcxWVwLEIO7qphdLcsk5x4GsOY")
        println(ca.send(Message(agentid=6,touser = "Bailin_Song",text = TextContent("测试消息"))))
    }

    @Test
    fun send1() {
    }
}