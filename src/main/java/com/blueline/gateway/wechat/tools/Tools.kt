package com.blueline.gateway.wechat.tools

import com.alibaba.fastjson.JSON
import org.json.XML


fun xml2JsonString(xml:String):String{
    return XML.toJSONObject(xml).toString()
}

fun xml2Map(xml:String):Map<out String, Any>{
    return JSON.parseObject(xml2JsonString(xml),Map::class.java)["xml"] as Map<out String, Any>
}