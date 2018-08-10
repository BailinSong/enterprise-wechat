package com.blueline.gateway.wechat.domain.model

data class InviteResult(val errcode: Int = 0,
                        val invalidtag: List<Int>?,
                        val errmsg: String = "",
                        val invalidparty: List<Int>?,
                        val invaliduser: List<String>?):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}