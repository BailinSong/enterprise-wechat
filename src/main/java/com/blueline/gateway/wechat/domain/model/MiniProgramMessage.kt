package com.blueline.gateway.wechat.domain.model

data class MiniProgramMessage(val miniprogramNotice: MiniprogramNotice,
                              val touser: String = "",
                              val msgtype: String = "")