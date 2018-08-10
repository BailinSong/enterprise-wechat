package com.blueline.gateway.wechat.domain.model

data class TagUsers(val userlist: List<String>?,
                    val partylist: List<Integer>?,
                    val tagid: Int = 0)