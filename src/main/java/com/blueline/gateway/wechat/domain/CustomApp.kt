package com.blueline.gateway.wechat.domain

import com.blueline.gateway.wechat.domain.model.*
import com.blueline.gateway.wechat.tools.httpGet
import com.blueline.gateway.wechat.tools.httpPostJson

class CustomApp(val appId:Int,corpid: String, secret: String) : EnterpriseApp(corpid, secret) {


    fun send(message: Message): AppMessageResult {
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=${token()}", message)
        }
    }
    fun send(message: MiniProgramMessage): AppMessageResult {
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=${token()}", message)
        }
    }


}