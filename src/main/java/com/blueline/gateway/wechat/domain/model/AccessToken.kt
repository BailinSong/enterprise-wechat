package com.blueline.gateway.wechat.domain.model

data class AccessToken(val errcode: Int = 0,
                       val accessToken: String? = "",
                       val errmsg: String = "",
                       val expiresIn: Int = 0):IResult{
    override fun getErrCode(): Int {
        return errcode
    }
}