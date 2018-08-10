package com.blueline.gateway.wechat.domain.model

data class ExternalAttrItem(val name: String = "",
                            val text: Text?,
                            val web: Web?,
                            val miniprogram: MiniProgram?,
                            val type: Int = 0)