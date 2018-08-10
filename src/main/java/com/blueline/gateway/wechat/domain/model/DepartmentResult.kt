package com.blueline.gateway.wechat.domain.model

data class DepartmentResult(val errcode: Int = 0,
                            val errmsg: String = "",
                            val id: Int = 0):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}