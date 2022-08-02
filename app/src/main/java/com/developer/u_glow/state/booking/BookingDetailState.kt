package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.adapter.OfferAdapter

sealed class BookingDetailState {
    object Init : BookingDetailState()
    data class SetOfferAdapter(var adapter: OfferAdapter?): BookingDetailState()
}