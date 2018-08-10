package com.blueline.gateway.wechat.controller

import com.blueline.gateway.wechat.domain.model.IResult
import com.blueline.gateway.wechat.service.AddressBookService
import com.blueline.gateway.wechat.service.CustomAppService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/API")
class CustomAppController{

    @Autowired
    lateinit var service: CustomAppService

    @RequestMapping(method = [RequestMethod.POST],value = "/app/message")
    fun sentTextMessage(userID: String, content: String): IResult {
        return service.sentTextMessage(userID,content)
    }

    @RequestMapping(method = [RequestMethod.GET],value = "/app/message/{userID}")
    fun sentTextMessageByGet(@PathVariable("userID")userID: String, content: String): IResult {
        return service.sentTextMessage(userID,content)
    }

}