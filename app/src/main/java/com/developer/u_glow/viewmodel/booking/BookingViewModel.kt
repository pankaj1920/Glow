package com.developer.u_glow.viewmodel.booking

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.model.dto.BookingData
import com.developer.u_glow.model.dto.OpenBookingData
import com.developer.u_glow.state.booking.BookingState
import com.developer.u_glow.util.Constants

class BookingViewModel : BaseViewModel<BookingState>() {

    private var bookingList: MutableList<BookingData>? = null
    private var bookingAdapter: BookingAdapter? = null

    private var state: BookingState = BookingState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        initBooking()
    }

    private fun initBooking() {
        bookingList = ArrayList()
        for (i in 1..5) {
            bookingList?.add(BookingData())
        }
        bookingAdapter = BookingAdapter(bookingList as ArrayList<OpenBookingData>, true, Constants.GlowType.booking, this)
        state = BookingState.UpdateBookingAdapter(bookingAdapter)
    }

    fun onClickGlow(position: Int, data: OpenBookingData){
        state = BookingState.NavigateToDetail(data)
    }
}