package com.blueline.gateway.wechat.domain

import com.blueline.gateway.wechat.domain.model.Invite
import com.blueline.gateway.wechat.domain.model.User
import com.blueline.gateway.wechat.domain.model.UserIDList
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import kotlin.concurrent.thread

class AddressBookTest{

    val ab=AddressBook("wxb861157903009f2b","Sc2Ah4fMBtp-a7yn2TFd1Xb76ZLj-e6BHMqk_s0yEMg")


    @Test
    fun TestUser() {

        ab.batchDeleteUser(UserIDList(arrayListOf("10000000001","10000000002"))).errcode
        var user1=User(userid="10000000001",name="宋柏霖1",email = "bailinsong@me.com",gender="1",department = arrayListOf(1))
        assertEquals(ab.createUser(user1).errcode,0)
        var user2=User(userid="10000000002",name="宋柏霖2",email = "bailinsong1@me.com",gender="1",department = arrayListOf(1))
        assertEquals(ab.createUser(user2).errcode,0)
         user1=User(userid="10000000001",name="宋柏霖3",email = "bailinsong@me.com",gender="1",department = arrayListOf(1))
        assertEquals(ab.updateUser(user1).errcode,0)
         user2=User(userid="10000000002",name="宋柏霖4",email = "bailinsong1@me.com",gender="1",department = arrayListOf(1))
        assertEquals(ab.updateUser(user2).errcode,0)
        assertEquals(ab.deleteUser("10000000001").errcode,0)
        assertEquals(ab.deleteUser("10000000002").errcode,0)
        user1=User(userid="10000000001",name="宋柏霖1",email = "bailinsong@me.com",gender="1",department = arrayListOf(1))
        assertEquals(ab.createUser(user1).errcode,0)
        user2=User(userid="10000000002",name="宋柏霖2",email = "bailinsong1@me.com",gender="1",department = arrayListOf(1))
        assertEquals(ab.createUser(user2).errcode,0)
        assertEquals(ab.listUser(1, 1).errcode,0)
        assertEquals(ab.listUserInfo(1, 1).errcode,0)
        assertEquals(ab.getUser("10000000001").errcode,0)
    }



    @Test
    fun TestDepartment() {

    }

    @Test
    fun updateDepartment() {
    }

    @Test
    fun deleteDepartment() {
    }

    @Test
    fun listDepartment() {
    }

    @Test
    fun createTag() {
    }

    @Test
    fun updateTag() {
    }

    @Test
    fun deleteTag() {
    }

    @Test
    fun addTagUsers() {
    }

    @Test
    fun deleteTagUsers() {
    }

    @Test
    fun listTags() {
        var user2=User(userid="Bailin_Song",name="宋柏霖",mobile = "18611111111",email = "17717776@qq.com",gender="1",department = arrayListOf(1))
        assertEquals(ab.updateUser(user2).errcode,0)
    }

    @Test
    fun inviteUsers() {
        ab.inviteUsers(invite = Invite(user= listOf("Bailin_Song","10000000001")))
    }
}
