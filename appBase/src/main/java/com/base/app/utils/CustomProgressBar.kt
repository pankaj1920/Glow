package com.base.app.utils

import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import com.base.app.R
import kotlinx.android.synthetic.main.loader.view.*

class CustomProgressBar {

    var dialog: Dialog? = null

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    fun show(context: Context, title:CharSequence?): Dialog {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.loader, null)

        if (title != null) {
//            view.cp_title.visibility = View.VISIBLE
//            view.cp_title.text = title
        }else
//            view.cp_title.visibility = View.GONE
        view.cp_bg_view.setBackgroundColor(Color.parseColor("#60000000")) //Background Color
        view.cp_cardview.setCardBackgroundColor(Color.parseColor("#70000000")) //Box Color
//        setColorFilter(view.cp_pbar.indeterminateDrawable,
//            ResourcesCompat.getColor(context.resources, R.color.light_gray, null)) //Progress Bar Color
        view.cp_title.setTextColor(Color.WHITE) //Text Color

        dialog = Dialog(context, R.style.CustomProgressBarTheme)
        dialog!!.setContentView(view)
        dialog!!.show()

        return dialog!!
    }

    fun setColorFilter(drawable: Drawable, color:Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}