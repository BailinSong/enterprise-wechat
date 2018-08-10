package com.blueline.gateway.wechat.domain.model

data class TagUsersResult(val errcode: Int = 0,
                          val errmsg: String = "",
                          val invalidparty: List<Integer>?,
                          val invalidlist: String? = ""):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}