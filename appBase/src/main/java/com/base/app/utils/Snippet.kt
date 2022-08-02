package com.base.app.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import com.base.app.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun <T> T.moshiObjToString(type: Class<T>): String {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(type)
    return jsonAdapter.toJson(this)
}

fun <T> String.moshiStringToObj(type: Class<T>): T? {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(type)
    return jsonAdapter.fromJson(this)
}

fun Double.roundOff(): String {
    val format = DecimalFormat("0.00")
    return format.format(this).replace(",", ".")
}


fun ViewModel.runInBack(process: () -> Unit, result: () -> Unit) {
    this.viewModelScope.launch(Dispatchers.IO) {
        async { process() }.await()
        launch(Dispatchers.Main) {
            result()
        }
    }
}

fun ViewModel.debounce(
    waitMs: Long = 1000L,
    debounceJob: Job?,
    destinationFunction: () -> Unit
): Job? {
    debounceJob?.cancel()
    return this.viewModelScope.launch {
        delay(waitMs)
        destinationFunction()
    }
}

fun Context.shareInfo(content: String) {
    val sendIntent = Intent()
    sendIntent.action = Intent.ACTION_SEND
    sendIntent.putExtra(Intent.EXTRA_TEXT, content)
    sendIntent.type = "text/plain"
    this.startActivity(Intent.createChooser(sendIntent, "Send to"))
}

fun String.getFormatedPhone(): String {
    if (this.isNotEmpty()) {
        val formattedString = this.replace("-", "").replace("+", "")
        var finalFormattedString = ""
        finalFormattedString = when {
            formattedString.length < 4 -> {
                formattedString.substring(0)
            }
            formattedString.length < 7 -> {
                formattedString.substring(0, 3) + "-" + formattedString.substring(3)
            }
            else -> {
                formattedString.substring(0, 3) + "-" + formattedString.substring(
                    3,
                    6
                ) + "-" + formattedString.substring(6)
            }
        }
        return finalFormattedString
    }
    return this
}

fun String.convertTimeFormat(expectedFormat: String, finalFormat: String): String? {
    try {
        val customFormat = SimpleDateFormat(
            expectedFormat,
            Locale.getDefault()
        )
        customFormat.timeZone = TimeZone.getDefault()
        //return new SimpleDateFormat("dd yyyy MMM  hh:mm a", Locale.getDefault()).format(customFormat.parse(time));
        val outputFormat = SimpleDateFormat(finalFormat, Locale.getDefault())
//            outputFormat.timeZone = TimeZone.getTimeZone("UTC")
        return outputFormat.format(customFormat.parse(this)!!)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}

fun Context.getDeviceId(): String {
    return Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
}

fun Uri.getRealPathFromURI(resolvers: ContentResolver): String? {
    val result: String
    val cursor: Cursor? = resolvers.query(this, null, null, null, null)
    if (cursor == null) { // Source is Dropbox or other similar local file path
        result = this.path!!
    } else {
        cursor.moveToFirst()
        val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        result = cursor.getString(idx)
        cursor.close()
    }
    return result
}

fun NavController.isOnBackStack(@IdRes id: Int): Boolean = try {
    getBackStackEntry(id); true
} catch (e: Throwable) {
    false
}

fun AppCompatImageView.loadCurvedImage(url: String, dimen: Float){
    Glide.with(this.context)
        .load(url)
        .transform(CenterInside(), RoundedCorners(dimen.toInt()))
        .into(this)
}

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w\\.+-]+@([\\w\\+-]+\\.)+[a-zA-Z]{2,3}$"
    val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    return pattern.matcher(this).matches()
}

fun String.encrypt(): String? {
    val crypt = CryptLib()
    return try {
        crypt.encryptPlainTextWithRandomIV(this, BuildConfig.AES_SECRET)
    } catch (e: Exception) {
        ""
    }
}

fun String.decrypt(): String? {
    val crypt = CryptLib()
    return try {
        crypt.decryptCipherTextWithRandomIV(this, BuildConfig.AES_SECRET)
    } catch (e: Exception) {
        ""
    }
}



