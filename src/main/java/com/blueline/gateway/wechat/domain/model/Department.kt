package com.blueline.gateway.wechat.domain.model

data class Department(var name: String = "",
                      var id: Int = 0,
                      var parentid: Int = 0,
                      var order: Int = 0)