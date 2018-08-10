package com.blueline.gateway.wechat.domain.model

data class MPArticlesItem(val thumbMediaId: String = "",
                          val author: String = "",
                          val digest: String = "",
                          val contentSourceUrl: String = "",
                          val title: String = "",
                          val content: String = "")