package com.developer.u_glow.viewmodel.booking

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.adapter.FilterAdapter
import com.developer.u_glow.model.dto.BookingData
import com.developer.u_glow.model.dto.FilterData
import com.developer.u_glow.model.dto.OpenBookingData
import com.developer.u_glow.state.booking.BookingState
import com.developer.u_glow.state.booking.DashboardState
import com.developer.u_glow.state.booking.OpenGlowState
import com.developer.u_glow.util.Constants

class OpenGlowViewModel : BaseViewModel<OpenGlowState>() {

    private var bookingList: MutableList<BookingData>? = null
    private var bookingAdapter: BookingAdapter? = null

    private var state: OpenGlowState = OpenGlowState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        initBooking()
        initFilter()
    }

    private fun initFilter() {
        val list = ArrayList<FilterData>()
        list.add(FilterData())
        list.add(FilterData())
        list.add(FilterData())
        state = OpenGlowState.UpdateFilterAdapter(FilterAdapter(list))

    }

    private fun initBooking() {
        bookingList = ArrayList()
        for (i in 1..5) {
            bookingList?.add(BookingData())
        }
        bookingAdapter = BookingAdapter(bookingList as ArrayList<OpenBookingData>, false, Constants.GlowType.openGlow, this)
        state = OpenGlowState.UpdateBookingAdapter(bookingAdapter)
    }

    fun onClickMakeOffer(position: Int, data: OpenBookingData){
        state = OpenGlowState.NavigateToDetails(data)
    }
}