package com.blueline.gateway.wechat.domain.model

data class TagUserList(val errcode: Int = 0,
                       val userlist: List<UserlistItem>?,
                       val tagname: String = "",
                       val partylist: List<Integer>?,
                       val errmsg: String = ""):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}