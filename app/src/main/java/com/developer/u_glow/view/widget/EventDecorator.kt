package com.developer.u_glow.view.widget

import android.content.Context
import android.graphics.Color
import android.net.ParseException
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.developer.u_glow.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import org.threeten.bp.LocalDate
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "yyyy-MM-dd"

class EventDecorator(
    var context: Context, private val drawable: Int? = null,
    calendarDays: List<CalendarDay>?
) : DayViewDecorator {

    private val dates: HashSet<CalendarDay> = HashSet(calendarDays)

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        drawable?.let {
            ContextCompat.getDrawable(
                context,
                it
            )?.let { view.setSelectionDrawable(it) }
        }
        view.addSpan(ForegroundColorSpan(Color.BLACK))
    }
}

fun MaterialCalendarView.setEvent(dateList: MutableList<String>, context: Context) {
    val localDateList: MutableList<CalendarDay> = ArrayList()
    for (string in dateList) {
        val calendar: LocalDate? = getLocalDate(string)
        if (calendar != null) {
            localDateList.add(CalendarDay.from(calendar))
        }
    }

    setDecor(localDateList, R.drawable.bg_curve_white_day, context, this)

}

fun setDecor(
    calendarDayList: List<CalendarDay>?,
    drawable: Int? = null, context: Context,
    view: MaterialCalendarView
) {
    view.addDecorators(
        EventDecorator(
            context, drawable, calendarDayList
        )
    )
}

fun getLocalDate(date: String?, format: String = DATE_FORMAT): LocalDate? {
    val sdf = SimpleDateFormat(format, Locale.ENGLISH)
    return try {
        val input: Date = sdf.parse(date)
        val cal: Calendar = Calendar.getInstance()
        cal.time = input
        LocalDate.of(
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH) + 1,
            cal.get(Calendar.DAY_OF_MONTH)
        )
    } catch (e: NullPointerException) {
        null
    } catch (e: ParseException) {
        null
    }
}