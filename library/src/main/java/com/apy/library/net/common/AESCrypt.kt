package com.apy.library.net.common
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


/**
 * Create by JohnRambo at 2018/9/8
 */

/*fun String.AESEncrypt(key: String, offset: String) = AESCrypt.encrypt(this, key, offset)

fun String.AESDecrypt(key: String, offset: String) = AESCrypt.decrypt(this, key, offset)*/

object AESCrypt {
    val cipher by lazy { Cipher.getInstance("AES/CBC/PKCS5Padding") }

    /**
     * target: 加密的字符串, key:秘钥 offset:偏移量 mode:1加密 2解密
     */
    fun encrypt(target: String, key: String, offset: String): String {
        val secretKeySpec = SecretKeySpec(key.toByteArray(), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(offset.toByteArray()))
        val result = cipher.doFinal(target.toByteArray())
        return Base64.encodeBytes(result)
    }

    fun decrypt(target: String, key: String, offset: String): String {
        val decode = Base64.decode(target)
        val secretKeySpec = SecretKeySpec(key.toByteArray(), "AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec(offset.toByteArray()))
        return cipher.doFinal(decode).toString()
    }


}