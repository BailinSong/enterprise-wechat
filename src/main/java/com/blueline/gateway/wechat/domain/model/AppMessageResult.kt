package com.blueline.gateway.wechat.domain.model

data class AppMessageResult(val errcode: Int = 0,
                            val invalidtag: String? = "",
                            val errmsg: String = "",
                            val invalidparty: String? = "",
                            val invaliduser: String? = ""):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}