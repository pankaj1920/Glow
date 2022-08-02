package com.base.app.utils.security

import android.util.Base64
import okhttp3.RequestBody
import okio.Buffer
import java.io.IOException
import java.security.SecureRandom
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.jvm.Throws


/**
 * A utitliy class for encryption and decryption logic
 *
 *
 * You can implement any kind of encryption and decryption logic per your security requirements
 *
 *
 * The Encryption/Decryption is not to be used for a production app.
 * This is just a demo, proper security guidelines should be followed
 */
object CryptoUtil {

    private var KEY_AES = "06fe746c4d7174b827e5599eac0e26f2"
    private var IV = getRandomString(16)
    private const val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"

    @JvmStatic
    @Throws(Exception::class)
    fun encrypt(value: String): String? {
        try {
            val key = KEY_AES.toByteArray(charset("UTF-8"))
            val ivs = IV.toByteArray(charset("UTF-8"))
            val cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING")
            val secretKeySpec = SecretKeySpec(key, "AES")
            val paramSpec: AlgorithmParameterSpec = IvParameterSpec(ivs)
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, paramSpec)
            return Base64.encodeToString(
                cipher.doFinal(value.toByteArray(charset("UTF-8"))),
                Base64.DEFAULT
            )

        } catch (e: Exception) {
            e.printStackTrace()
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        }
        return null
    }

    @JvmStatic
    @Throws(Exception::class)
    fun decrypt(value: String?, localIV: String): String? {
        try {
            val encryted_bytes: ByteArray = Base64.decode(value, Base64.DEFAULT)
            val key = KEY_AES.toByteArray(charset("UTF-8"))
            val ivs = localIV.toByteArray(charset("UTF-8"))
            val secretKeySpec = SecretKeySpec(key, "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING")
            val paramSpec: AlgorithmParameterSpec = IvParameterSpec(ivs)
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, paramSpec)
            val decrypted = cipher.doFinal(encryted_bytes)
            return String(decrypted)
        } catch (e: Exception) {
            e.printStackTrace()
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
        }
        return null
    }


    private fun getRandomString(sizeOfRandomString: Int): String {
        val random = SecureRandom()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return sb.toString()
    }

    @JvmStatic
    @Throws(IOException::class)
    fun requestBodyToString(requestBody: RequestBody): String {
        val buffer = Buffer()
        requestBody.writeTo(buffer)
        return buffer.readUtf8()
    }

    @JvmStatic
    fun getIv(): String {
        return IV
    }

}