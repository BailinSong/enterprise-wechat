package com.blueline.gateway.wechat.domain.model

data class UserlistItem(val name: String = "",
                        val department: List<Int>?,
                        val userid: String = "")