package com.blueline.gateway.wechat.service

import com.blueline.gateway.wechat.domain.AddressBook
import com.blueline.gateway.wechat.domain.model.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment


@Component
class AddressBookService {

    @Autowired
    lateinit var env: Environment

    @Value("\${ewechat.corpid}")
    lateinit var corpID:String
    @Value("\${ewechat.addressbook.secret}")
    lateinit var secret:String

    @Autowired
    lateinit var addressBook:AddressBook

    @Bean
    fun addressBook():AddressBook{
        return AddressBook(corpID, secret)
    }

    fun createUser(userID: String, Name: String, mobile: String = "", email: String = "", sex: String = "0", department: Array<Int> = arrayOf(1)): Result {
        var user = User(userid = userID, name = Name, mobile = mobile, email = email, gender = sex, department = department.toList())
        return addressBook.createUser(user)
    }

    fun updateUser(userID: String, Name: String, mobile: String = "", email: String = "", sex: String = "0", department: Array<Int> = arrayOf(1)): Result {
        var user = User(userid = userID, name = Name, mobile = mobile, email = email, gender = sex, department = department.toList())
        return addressBook.updateUser(user)
    }

    fun getUser(userID: String): User {
        return addressBook.getUser(userID)
    }

    fun deleteUser(userID: String): Result {
        return addressBook.deleteUser(userID)
    }

    fun listUser(): UserList {
        return addressBook.listUser()
    }

    fun listUserInfo(): UserInfoList {
        return addressBook.listUserInfo()
    }

    fun inviteUsers(user:List<String>?= listOf(), tag:List<Int>?=listOf(), party:List<Int>?=listOf()): InviteResult {
        var invite = Invite(tag,user,party)
        return addressBook.inviteUsers(invite)
    }
}