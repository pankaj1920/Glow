package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.model.dto.BookingData
import com.developer.u_glow.model.dto.OpenBookingData

sealed class BookingState {
    object Init : BookingState()
    data class UpdateBookingAdapter(var adapter: BookingAdapter?) : BookingState()
    data class NavigateToDetail(var data: OpenBookingData) : BookingState()
}