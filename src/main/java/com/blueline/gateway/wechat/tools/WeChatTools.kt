package com.blueline.gateway.wechat.tools

import java.io.DataInputStream

fun convertMessage(aesKey:String, encrypt:String):String{
    val AESByes = Base64.decode(aesKey)
    val dEchostr = Aes.decTextToByte(encrypt, AESByes)
    return dEchostr.inputStream().use { baseData ->
        baseData.skip(16)
        DataInputStream(baseData).use {
            val msgLen = it.readInt()
            val msgBytes = ByteArray(msgLen)
            it.read(msgBytes)
            msgBytes.toString(charset("UTF-8"))
        }
    }
}