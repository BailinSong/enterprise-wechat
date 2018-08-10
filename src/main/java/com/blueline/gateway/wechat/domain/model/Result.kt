package com.blueline.gateway.wechat.domain.model

data class Result(val errcode: Int = 0,//返回码
                  val errmsg: String = ""//	对返回码的文本描述内容
):IResult{
    override fun getErrCode():Int {
        return errcode
    }

}