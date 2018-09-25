package com.blueline.gateway.wechat.tools

import com.alibaba.fastjson.JSON
import org.apache.http.NameValuePair
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.*
import org.apache.http.entity.AbstractHttpEntity
import org.apache.http.entity.ByteArrayEntity
import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.springframework.http.HttpStatus
import java.io.IOException
import java.net.URI
import java.util.*


/**
 * post请求传输map数据
 *
 * @param url
 * @param map
 * @param encoding
 * @return
 * @throws ClientProtocolException
 * @throws IOException
 */
@Throws(ClientProtocolException::class, IOException::class)
private fun http(method: HttpRequestBase, url: String, map: Map<String, String>?, encoding: String="utf-8",head: Array<Pair<String,String>>?=null): String {
    var result = ""

    // 创建httpclient对象
    val httpClient = HttpClients.createDefault()
    // 创建post方式请求对象

    method.uri= URI.create(url)


    // 装填参数
    val nameValuePairs = ArrayList<NameValuePair>()
    if (map != null) {
        for ((key, value) in map) {
            nameValuePairs.add(BasicNameValuePair(key, value))
        }
    }

    // 设置参数到请求对象中
    if(method is HttpEntityEnclosingRequestBase)
    method.entity = UrlEncodedFormEntity(nameValuePairs, encoding)

    // 设置header信息
    // 指定报文头【Content-type】、【User-Agent】


    method.setHeader("Content-type", "application/x-www-form-urlencoded")
    method.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)")

    head?.forEach {
        it?.apply {
            method.setHeader(this.first,this.second)
        }
    }

    // 执行请求操作，并拿到结果（同步阻塞）
    val response = httpClient.execute(method)
    // 获取结果实体
    // 判断网络连接状态码是否正常(0--200都数正常)
    if (HttpStatus.OK.value() == response.statusLine.statusCode) {
        result = EntityUtils.toString(response.entity, encoding)
    }
    // 释放链接
    response.close()

    return result
}

/**
 * post请求传输json数据
 *
 * @param url
 * @param json
 * @param encoding
 * @return
 * @throws ClientProtocolException
 * @throws IOException
 */
@Throws(ClientProtocolException::class, IOException::class)
private fun http(method: HttpRequestBase, url: String, json: String, encoding: String="utf-8",head:Array<Pair<String,String>>?=null): String {

    return http(method,url,json,ContentType.APPLICATION_JSON,encoding,head)

}

/**
 * post请求传输json数据
 *
 * @param url
 * @param json
 * @param encoding
 * @return
 * @throws ClientProtocolException
 * @throws IOException
 */
@Throws(ClientProtocolException::class, IOException::class)
private fun http(method: HttpRequestBase, url: String, data: Any,cType:ContentType, encoding: String="utf-8",head:Array<Pair<String,String>>?=null): String {

    var result = ""

    // 创建httpclient对象
    val httpClient = HttpClients.createDefault()

    // 创建post方式请求对象
    method.uri= URI.create(url)

    head?.forEach {
        it?.apply {
            method.setHeader(this.first,this.second)
        }
    }

    val entity: AbstractHttpEntity =when(cType){
        ContentType.APPLICATION_FORM_URLENCODED->{
            val nameValuePairs = ArrayList<NameValuePair>()
            if (data != null) {
                if(data is Map<*,*>)
                for ((key, value) in data) {
                    nameValuePairs.add(BasicNameValuePair(key.toString(), value.toString()))
                }
            }
            UrlEncodedFormEntity(nameValuePairs, encoding)
        }
        ContentType.DEFAULT_BINARY->{
            ByteArrayEntity(data as ByteArray,cType)
        }
        else->{
            StringEntity(data.toString(), cType).apply {
                this.setContentEncoding(encoding)
            }

        }
    }

    if(method is HttpEntityEnclosingRequestBase)
        method.entity = entity

    // 执行请求操作，并拿到结果（同步阻塞）
    val response = httpClient.execute(method)

    // 获取结果实体
    // 判断网络连接状态码是否正常(0--200都数正常)
    if (HttpStatus.OK.value() == response.statusLine.statusCode) {
        result = EntityUtils.toString(response.entity, encoding)
    }
    // 释放链接
    response.close()

    return result
}

/**
 * get请求传输数据
 *
 * @param url
 * @param encoding
 * @return
 */
fun httpGet(url: String, encoding: String="utf-8"): String {
   return http(HttpGet(),url,null,encoding)
}

fun httpPostMap(url: String,map: Map<String, String>?, encoding: String="utf-8"): String {
    return http(HttpPost(),url,map,encoding)
}

fun httpPutMap(url: String,map: Map<String, String>?, encoding: String="utf-8"): String {
    return http(HttpPut(),url,map,encoding)
}

fun httpDeleteMap(url: String,map: Map<String, String>?, encoding: String="utf-8"): String {
    return http(HttpDelete(),url,map,encoding)
}

fun httpPostJson(url: String,json: String, encoding: String="utf-8"): String {
    return http(HttpPost(),url,json,encoding)
}

fun httpPutJson(url: String, json: String, encoding: String="utf-8"): String {
    return http(HttpPut(),url,json,encoding)
}

fun httpDeleteJson(url: String,json: String, encoding: String="utf-8"): String {
    return http(HttpDelete(),url,json,encoding)
}

fun httpPostJson(url: String,obj: Any, encoding: String="utf-8"): String {

    return http(HttpPost(),url, JSON.toJSONString(obj),encoding)
}

fun httpPost(url: String,obj: Any, cType:ContentType=ContentType.DEFAULT_TEXT,encoding: String="utf-8",head:Array<Pair<String,String>>?=null): String {

    return http(HttpPost(),url, obj,cType,encoding,head)
}

fun httpPutJson(url: String, obj: Any, encoding: String="utf-8"): String {
    return http(HttpPut(),url,JSON.toJSONString(obj),encoding)
}



fun httpDeleteJson(url: String,obj: Any, encoding: String="utf-8"): String {
    return http(HttpDelete(),url,JSON.toJSONString(obj),encoding)
}