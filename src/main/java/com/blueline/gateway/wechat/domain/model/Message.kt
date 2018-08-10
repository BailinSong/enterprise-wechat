package com.blueline.gateway.wechat.domain.model

data class Message(var agentid: Int = 0,
                   var touser: String? = "",
                   var safe: Int = 0,
                   var totag: String? = "",
                   var text: TextContent?=null,
                   var image:Image?=null,
                   var voice:Voice?=null,
                   var video:Video?=null,
                   var file:File?=null,
                   var textcard:TextCard?=null,
                   var news:News?=null,
                   var mpnews:MPNews?=null,
                   var msgtype: MSG_TYPE = MSG_TYPE.text,
                   var toparty: String? = "")