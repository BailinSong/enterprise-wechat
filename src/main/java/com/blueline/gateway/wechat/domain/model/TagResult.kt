package com.blueline.gateway.wechat.domain.model

data class TagResult(val errcode: Int = 0,
                     val errmsg: String = "",
                     val tagid: Int = 0):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}