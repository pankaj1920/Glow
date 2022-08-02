package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.adapter.GalleryAdapter
import com.developer.u_glow.adapter.OfferAdapter
import com.developer.u_glow.state.profile.GalleryFragmentState

sealed class DetailsState {
    object Init : DetailsState()
    data class UpdateGalleryAdapter(var adapter: GalleryAdapter?) : DetailsState()
}
