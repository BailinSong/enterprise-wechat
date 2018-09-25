package com.blueline.gateway.wechat.tools

import java.nio.charset.Charset
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * AES static function for different key and iv
 * mode: AES/CBC/PKCS5Padding
 * text input encoding: utf-8
 * text output encoding: base64
 *
 */
object Aes {

    private val IVK = byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    //	public static final String bytesPrint(byte[] in) {
    //		StringBuilder sb = new StringBuilder();
    //		for (int i = 0; i < in.length; i++) {
    //			sb.append(in[i]).append(",");
    //		}
    //		String out = sb.toString();
    //		System.out.println(out);
    //		return out;
    //	}


    fun encBytes(srcBytes: ByteArray, key: ByteArray,
                 newIv: ByteArray=IVK): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val skeySpec = SecretKeySpec(key, "AES")
        val iv = IvParameterSpec(newIv)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
        return cipher.doFinal(srcBytes)
    }



    fun encText(sSrc: String, key: ByteArray, newIv: ByteArray=IVK): String {
        val srcBytes = sSrc.toByteArray(charset("utf-8"))
        val encrypted = encBytes(srcBytes, key, newIv)
        return Base64.encode(encrypted)
    }


    fun decBytes(srcBytes: ByteArray, key: ByteArray,
                 newIv: ByteArray=IVK): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val skeySpec = SecretKeySpec(key, "AES")
        val iv = IvParameterSpec(newIv)
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
        return cipher.doFinal(srcBytes)
    }


    fun decText(sSrc: String, key: ByteArray, newIv: ByteArray=IVK): String {
        return String(decTextToByte(sSrc,key,newIv), Charset.forName("utf-8"))
    }
    fun decTextToByte(sSrc: String, key: ByteArray, newIv: ByteArray=IVK): ByteArray {
        val srcBytes = Base64.decode(sSrc)
        return decBytes(srcBytes, key, newIv)

    }


}