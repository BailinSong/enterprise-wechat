package com.blueline.gateway.wechat.domain.model

data class MiniprogramNotice(val contentItem: List<ContentItemItem>?,
                             val appid: String = "",
                             val description: String = "",
                             val emphasisFirstItem: String = "",
                             val page: String = "",
                             val title: String = "")