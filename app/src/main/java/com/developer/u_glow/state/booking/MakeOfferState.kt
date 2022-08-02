package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.adapter.OfferAdapter

sealed class MakeOfferState {
    object Init : MakeOfferState()
    data class SetOfferAdapter(var adapter: OfferAdapter?): MakeOfferState()
}