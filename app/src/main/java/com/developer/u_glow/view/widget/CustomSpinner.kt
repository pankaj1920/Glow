package com.developer.u_glow.view.widget

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner


class CustomSpinner : AppCompatSpinner {

    interface OnSpinnerEventsListener {
        fun onPopupWindowOpened(spinner: Spinner?)
        fun onPopupWindowClosed(spinner: Spinner?)
    }

    private var mListener: OnSpinnerEventsListener? = null
    private var mOpenInitiated = false

    constructor(context: Context) : super(context)

    constructor(context: Context, mode: Int) : super(context, mode)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int, mode: Int) : super(context, attrs, defStyle, mode)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int, mode: Int, popupTheme: Resources.Theme) : super(context, attrs, defStyle, mode, popupTheme)

    override fun performClick(): Boolean {
        mOpenInitiated = true
        mListener?.onPopupWindowOpened(this)
        return super.performClick()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (mOpenInitiated && hasFocus) {
            performClosedEvent()
        }
    }

    fun setSpinnerEventsListener(
        onSpinnerEventsListener: OnSpinnerEventsListener
    ) {
        mListener = onSpinnerEventsListener
    }

    private fun performClosedEvent() {
        mOpenInitiated = false
        if (mListener != null) {
            mListener!!.onPopupWindowClosed(this)
        }
    }
}