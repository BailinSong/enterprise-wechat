package com.blueline.gateway.wechat.domain.model

data class Invite(val tag: List<Int>?= listOf(),
                  val user: List<String>?= listOf(),
                  val party: List<Int>?= listOf())