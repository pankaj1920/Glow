package com.developer.u_glow.util

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View

class Dialog(var activity: Activity) {

    fun createAlert(layout: Int): Pair<View, androidx.appcompat.app.AlertDialog> {
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(layout, null)
        dialogBuilder.setView(dialogView)
        val alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return Pair(dialogView, alertDialog)
    }


}