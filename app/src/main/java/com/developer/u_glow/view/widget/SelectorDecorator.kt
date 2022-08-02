package com.developer.u_glow.view.widget

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.developer.u_glow.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

/**
 * Use a custom selector
 */
class SelectorDecorator(context: Activity) : DayViewDecorator {
    private val drawable: Drawable = ContextCompat.getDrawable(context, R.drawable.selector_calendar_days)!!
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return true
    }

    override fun decorate(view: DayViewFacade) {
        view.setSelectionDrawable(drawable)
    }

}