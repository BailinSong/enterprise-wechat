package com.blueline.gateway.wechat.domain.model

data class TagList(val errcode: Int = 0,
                   val errmsg: String = "",
                   val taglist: List<Tag>?):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}