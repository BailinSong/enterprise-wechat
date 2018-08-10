package com.blueline.gateway.wechat

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class EnterpriseApplication

fun main(args: Array<String>) {
    SpringApplication.run(EnterpriseApplication::class.java, *args)

}
