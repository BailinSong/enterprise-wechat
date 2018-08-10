package com.blueline.gateway.wechat.domain.model

import java.util.*

/**
 *
 */
data class User(var errcode: Int? = 0, //返回码
                var errmsg: String? = "", //对返回码的文本描述内容
                var isleader: Int? = 0,//上级字段，标识是否为上级；第三方仅通讯录应用可获取
                var gender: String? = "",//性别。0表示未定义，1表示男性，2表示女性
                var mobile: String? = "",//手机号码，第三方仅通讯录应用可获取
                var telephone: String? = "",//座机。第三方仅通讯录应用可获取
                var toInvite: Boolean? = true,//是否邀请该成员使用企业微信（将通过微信服务通知或短信或邮件下发邀请，每天自动下发一次，最多持续3个工作日），默认值为true。
                var userid: String = UUID.randomUUID().toString(),//成员UserID。对应管理端的帐号，企业内必须唯一。不区分大小写，长度为1~64个字节
                var englishName: String? = "",//英文名。长度为1-64个字节，由字母、数字、点(.)、减号(-)、空格或下划线(_)组成
                var enable: Int? = 1,//成员启用状态。1表示启用的成员，0表示被禁用。注意，服务商调用接口不会返回此字段
                var avatarMediaid: String? = "",//成员头像的mediaid，通过素材管理接口上传图片获得的mediaid
                var avatar:String?="",//头像url。注：如果要获取小图将url最后的”/0”改成”/100”即可。第三方仅通讯录应用可获取
                var name: String = "",//成员名称
                var extattr: Extattr?=null,//扩展属性，第三方仅通讯录应用可获取
                var position: String? = "",//职位信息；第三方仅通讯录应用可获取
                var department: List<Int>?= arrayListOf(1),//成员所属部门id列表
                var externalProfile: ExternalProfile?=null,//成员对外属性，字段详情见对外属性；第三方仅通讯录应用可获取
                var email: String? = "",//邮箱，第三方仅通讯录套件可获取
                var order: List<Int>?=null,//部门内的排序值，默认为0。数量必须和department一致，数值越大排序越前面。值范围是[0, 2^32)
                var status:Int? = 0,//激活状态: 1=已激活，2=已禁用，4=未激活。已激活代表已激活企业微信或已关注微工作台（原企业号）。未激活代表既未激活企业微信又未关注微工作台（原企业号）。
                var qrCode:String?=""//员工个人二维码，扫描可添加为外部联系人；第三方仅通讯录应用可获取
                      ):IResult{
    override fun getErrCode(): Int {
        return errcode?:0
    }

}