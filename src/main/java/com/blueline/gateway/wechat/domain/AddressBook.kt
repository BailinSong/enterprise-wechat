package com.blueline.gateway.wechat.domain

import com.blueline.gateway.wechat.domain.model.*
import com.blueline.gateway.wechat.tools.httpGet
import com.blueline.gateway.wechat.tools.httpPostJson

class AddressBook(corpid: String, secret: String) : EnterpriseApp(corpid, secret) {


    fun createUser(user: User): Result {
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=${token()}", user)
        }
    }

    fun deleteUser(userID: String): Result {
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=${token()}&userid=$userID")
        }
    }

    fun batchDeleteUser(userIDs:UserIDList):Result{
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=${token()}", userIDs)
        }
    }

    fun listUser(department: Int = 1, fetchChild: Int = 1): UserList {
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=${token()}&department_id=$department&fetch_child=$fetchChild")
        }
    }

    fun listUser(tagID: Int): TagUserList {
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=${token()}&tagid=$tagID")
        }
    }

    fun listUserInfo(department: Int = 1, fetchChild: Int = 1): UserInfoList {
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=${token()}&department_id=$department&fetch_child=$fetchChild")

        }
    }

    fun getUser(userID: String): User {
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=${token()}&userid=$userID")
        }
    }

    fun updateUser(user: User): Result {
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=${token()}", user)
        }

    }

    fun createDepartment(department:Department):DepartmentResult {
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=${token()}", department)
        }
    }

    fun updateDepartment(department:Department):Result {
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=${token()}", department)
        }
    }
    fun deleteDepartment(ID:Int):Result {
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=${token()}&id=$ID")
        }
    }

    fun listDepartment(ID:Int):DepartmentList {
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=${token()}&id=$ID")
        }
    }

    fun createTag(tag:Tag):TagResult {
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=${token()}",tag)
        }
    }

    fun updateTag(tag:Tag):Result {
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=${token()}",tag)
        }
    }

    fun deleteTag(tagID:Int):Result {
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token=${token()}&tagid=$tagID")
        }
    }

    fun addTagUsers(tagUsers: TagUsers):TagUsersResult{
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token=${token()}",tagUsers)
        }
    }

    fun deleteTagUsers(tagUsers: TagUsers):TagUsersResult{
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token=${token()}",tagUsers)
        }
    }

    fun listTags():TagList{
        return call {
            httpGet("https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=${token()}")
        }
    }

    fun inviteUsers(invite: Invite):InviteResult{
        return call {
            httpPostJson("https://qyapi.weixin.qq.com/cgi-bin/batch/invite?access_token=${token()}",invite)
        }
    }

}