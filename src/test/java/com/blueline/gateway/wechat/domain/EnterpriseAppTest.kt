package com.blueline.gateway.wechat.domain

import org.junit.Test

import org.junit.Assert.*

class EnterpriseAppTest {

    @Test
    fun getAccessToken() {
      println(  EnterpriseApp("wxb861157903009f2b",
                "MYwQmLzD0uhik7zjedcxWVwLEIO7qphdLcsk5x4GsOY").refreshAccessToken())
    }
}