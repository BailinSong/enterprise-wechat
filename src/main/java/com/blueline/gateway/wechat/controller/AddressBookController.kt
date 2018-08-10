package com.blueline.gateway.wechat.controller

import com.blueline.gateway.wechat.domain.model.IResult
import com.blueline.gateway.wechat.service.AddressBookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/API")
class AddressBookController{

    @Autowired
    lateinit var service: AddressBookService

    @RequestMapping(method = [RequestMethod.POST],value = "/user")
    fun creatUser(userID: String, Name: String, mobile: String = "", email: String = "", sex: String = "0",department: Array<Int> = arrayOf(1)): IResult {
        return service.createUser(userID,Name,mobile,email,sex,department)
    }

    @RequestMapping(method = [RequestMethod.PUT],value = "/user")
    fun updateUser(userID: String, Name: String, mobile: String = "", email: String = "", sex: String = "0",department: Array<Int> = arrayOf(1)): IResult {
        return service.updateUser(userID,Name,mobile,email,sex,department)
    }

    @RequestMapping(method = [RequestMethod.DELETE],value = "/user")
    fun deleteUser(userID: String): IResult {
        return service.deleteUser(userID)
    }

    @RequestMapping(method = [RequestMethod.GET],value = "/user")
    fun listUser(detail:Boolean=false): IResult {
        return if(detail){service.listUserInfo()}else{ service.listUser()}
    }

    @RequestMapping(method = [RequestMethod.GET],value = "/user/{userID}")
    fun listUser(@PathVariable("userID")userID: String): IResult {
        return service.getUser(userID)
    }

    @RequestMapping(method = [RequestMethod.POST],value = "/invite")
    fun inviteUser(user:List<String>?= listOf(), tag:List<Int>?=listOf(), party:List<Int>?=listOf()): IResult {
        return service.inviteUsers(user,tag,party)
    }



}