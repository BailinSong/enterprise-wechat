package com.blueline.gateway.wechat.domain.model

data class DepartmentList(val errcode: Int = 0,
                          val errmsg: String = "",
                          val department: List<Department>?):IResult{
    override fun getErrCode(): Int {
        return errcode
    }

}