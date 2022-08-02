package com.base.app.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Parcelable
import android.provider.Settings
import android.util.Base64
import android.util.Base64.encode
import android.util.Log
import com.base.app.utils.security.CryptoUtil
import com.squareup.moshi.Moshi
import id.zelory.compressor.Compressor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.regex.Pattern


class CodeSnippet(private val context: Context) {

    // Do something for marshmallow and above versions
    // do something for phones running an SDK before marshmallow
    val isAboveMarshmallow: Boolean
        get() {
            val currentApiVersion = Build.VERSION.SDK_INT
            return currentApiVersion >= Build.VERSION_CODES.M
        }

    // Do something for marshmallow and above versions
    // do something for phones running an SDK before marshmallow
    val isAboveLollipop: Boolean
        get() {
            val currentApiVersion = Build.VERSION.SDK_INT
            return currentApiVersion >= Build.VERSION_CODES.LOLLIPOP
        }

    fun <T> T.moshiObjToString(type: Class<T>): String {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(type)
        return jsonAdapter.toJson(this)
    }

    fun getContext(): Context {
        return context
    }

    fun <T> String.moshiStringToObj(type: Class<T>): T? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter(type)
        return jsonAdapter.fromJson(this)
    }

    fun hasNetworkConnection(): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var isConnected = false

        // Retrieve current status of connectivity
        connectivityManager.allNetworks.forEach { network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)

            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    isConnected = true
                    return@forEach
                }
            }
        }

        return isConnected

    }

    fun showNetworkSettings() {
        val chooserIntent = Intent.createChooser(
            getSettingsIntent(Settings.ACTION_DATA_ROAMING_SETTINGS),
            "Complete action using"
        )
        val networkIntents = ArrayList<Intent>()
        networkIntents.add(getSettingsIntent(Settings.ACTION_WIFI_SETTINGS))
        chooserIntent.putExtra(
            Intent.EXTRA_INITIAL_INTENTS,
            networkIntents.toTypedArray<Parcelable>()
        )
        chooserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivityBySettings(context, chooserIntent)
    }

    private fun getSettingsIntent(settings: String): Intent {
        return Intent(settings)
    }

    private fun startActivityBySettings(context: Context, intent: Intent) {
        context.startActivity(intent)
    }

    /**
     * Sha 256 encryption hashing
     * */
    fun hashPassword(password: String): Pair<String, String>? {
        CryptoUtil.encrypt(password)?.let {
            return Pair(it, CryptoUtil.getIv())
        }
        return null
    }

    fun getResources(): Resources {
        return context.resources
    }

    fun checkNames(data: String): Boolean {
        val emailPattern = "[a-zA-Z ]+"
        return data.trim().matches(emailPattern.toRegex())
    }

    fun checkEmail(data: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return data.trim().matches(emailPattern.toRegex())
    }

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.+-]+@([\\w\\+-]+\\.)+[a-zA-Z]{2,3}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).matches()
    }

    fun isContainsUppercase(value: String): Boolean {
        return Pattern.matches(".*[A-Z].*", value)
    }

    fun isContainsLowercase(value: String): Boolean {
        return Pattern.matches(".*[a-z].*", value)
    }

    fun isContainsNumber(value: String): Boolean {
        return Pattern.matches(".*\\d.*", value)
    }

    fun isContainsSymbol(value: String): Boolean {
        return Pattern.matches(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*", value)
    }

    fun prepareFilePart(partName: String, bitmap: Bitmap?): MultipartBody.Part? {
        return if (bitmap != null) {
            val file = compressImage(bitmap)
            val filepath = file.path
            if (filepath.isNotEmpty()) {
                val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())

                /* RequestBody.create(
                 "multipart/form-data".toMediaTypeOrNull(),
                 file
             )*/
                MultipartBody.Part.createFormData(partName, file.name, requestFile)
            } else {
                null
            }
        } else {
            null
        }
    }

    fun compressImage(bitmap: Bitmap): File {
//        return Compressor.compress(mContext, bitmapToFile())
        return Compressor(context)
            .setQuality(75)
            .setCompressFormat(Bitmap.CompressFormat.JPEG)
            .compressToFile(bitmapToFile(bitmap))
    }

    fun bitmapToFile(bitmap: Bitmap): File {
        val wrapper = ContextWrapper(context)
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.png")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    /*fun printHashKey() {
        try {
            val info: PackageInfo = context.packageManager
                .getPackageInfo(context.packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.i("keyhash", "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
//            Log.e(TAG, "printHashKey()", e)
        } catch (e: Exception) {
//            Log.e(TAG, "printHashKey()", e)
        }
    }*/

}