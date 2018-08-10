package com.blueline.gateway.wechat.domain.model

data class UserList(val errcode: Int = 0,
                    val userlist: List<UserlistItem>?,
                    val errmsg: String = ""):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}