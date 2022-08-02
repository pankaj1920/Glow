package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.adapter.FilterAdapter
import com.developer.u_glow.model.dto.BookingData
import com.developer.u_glow.model.dto.OpenBookingData

sealed class OpenGlowState {
    object Init : OpenGlowState()
    data class UpdateBookingAdapter(var adapter: BookingAdapter?) : OpenGlowState()
    data class NavigateToDetails(var data: OpenBookingData) : OpenGlowState()
    data class UpdateFilterAdapter(var adapter: FilterAdapter) : OpenGlowState()
}