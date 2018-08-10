package com.blueline.gateway.wechat.domain

import com.alibaba.fastjson.JSON
import com.blueline.gateway.wechat.domain.model.AccessToken
import com.blueline.gateway.wechat.domain.model.IResult
import com.blueline.gateway.wechat.tools.httpGet
import java.util.concurrent.atomic.AtomicBoolean

open class EnterpriseApp(corpID: String, secret: String) {

    val accessTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpID=$corpID&corpsecret=$secret"

    var accessToken: AccessToken = refreshAccessToken()

    var refreshAccessTokenCode = hashSetOf(40091, 41001,42001)

    val lock = java.lang.Object()

    val atomicBoolean = AtomicBoolean(true)


    fun token():String{
        return accessToken.accessToken!!
    }

    fun refreshAccessToken(): AccessToken {
        accessToken = JSON.parseObject(httpGet(accessTokenUrl), AccessToken::class.java)
        return accessToken
    }

    inline fun <reified T : IResult> call(block: () -> String): T {

        var reCall: Boolean

        var result: IResult

        val oldToken = accessToken

        do {

            if(accessToken.errcode!=0){
                throw RuntimeException("Access token For failure: $accessToken")
            }

            if (atomicBoolean.get().not()) {
                synchronized(lock) {
                    if (atomicBoolean.get().not()) {
                        lock.wait()
                    }
                }
            }
            val jsonString=block()
            println(jsonString)
            result = JSON.parseObject(jsonString, T::class.java)

            if (refreshAccessTokenCode.contains(result.getErrCode())) {
                reCall = true
                if (atomicBoolean.compareAndSet(true, false)) {
                    synchronized(lock) {
                        if (accessToken == oldToken) {
                            refreshAccessToken()
                            atomicBoolean.set(true)
                            lock.notifyAll()
                        }
                    }
                }
            } else {
                reCall = false
            }

        } while (reCall)
        println(result)
        return result as T
    }

}