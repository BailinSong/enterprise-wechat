package com.blueline.gateway.wechat.domain.model

data class UserInfoList(val errcode: Int = 0,
                        val userlist: List<User>?,
                        val errmsg: String = ""):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}