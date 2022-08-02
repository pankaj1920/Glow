package com.developer.u_glow.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Environment
import android.text.format.DateFormat
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import com.base.app.utils.Toaster
import com.developer.u_glow.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


fun Context.requestPermission(readPermissionGranted:Boolean,writePermissionGranted:Boolean,permissionLauncher:ActivityResultLauncher<Array<String>>):Boolean {

    var _readPermissionGranted=readPermissionGranted
    var _writePermissionGranted=writePermissionGranted
    val hasReadPermission = ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    val hasWritePermission = ContextCompat.checkSelfPermission(
       this,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    _readPermissionGranted = hasReadPermission
    _writePermissionGranted = hasWritePermission || minSdk29

    val permissionToRequest = mutableListOf<String>()

    if (!readPermissionGranted) {
        permissionToRequest.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }
    if (!writePermissionGranted) {
        permissionToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
    if (permissionToRequest.isNotEmpty()) {
        permissionLauncher.launch(permissionToRequest.toTypedArray())
    }

     return  _readPermissionGranted && _writePermissionGranted
}

fun Context.initDatePicker(textView: TextView){

    val c = Calendar.getInstance()
    val year =c.get(Calendar.YEAR)
    val month=c.get(Calendar.MONTH)
    val day=c.get(Calendar.DAY_OF_MONTH)

    val datePick= DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            textView.text = makeDateString(dayOfMonth, monthOfYear, year)
        },
        year,
        month,
        day)

    datePick.show()
}

private fun makeDateString(dayOfMonth: Int, month: Int, year: Int): String {

    return " $dayOfMonth / ${month + 1} / $year"
}

fun Context.initTimePicker(textView: TextView){
    var hour:Int
    var minute:Int
    val timePicker= TimePickerDialog(this,
        { p0, p1, p2 ->
            hour=p1
            minute=p2
            val calendar=Calendar.getInstance()
            calendar.set(0,0,0,hour,minute)
            textView.text= DateFormat.format("hh:mm aa",calendar)
        },12,0,false)

//        timePicker.updateTime(hour!!,minute!!)
    timePicker.show()
}


@SuppressLint("SimpleDateFormat")
 fun Context.createImageFile(): File? {
    // Create an image file name
    val timeStamp: String = SimpleDateFormat(R.string.time_format.toString()).format(Date())
    val storageDir: File? = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "SELFI_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    ).apply {
        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = absolutePath
    }
}


fun Activity.createAlert(layout: Int): Pair<View, androidx.appcompat.app.AlertDialog> {
    val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)
    val inflater = this.layoutInflater
    val dialogView = inflater.inflate(layout, null)
    dialogBuilder.setView(dialogView)
    val alertDialog = dialogBuilder.create()
    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    return Pair(dialogView, alertDialog)
}