package com.developer.u_glow.state.profile

import com.developer.u_glow.adapter.GalleryAdapter
import com.developer.u_glow.adapter.NotificationAdapter
import com.developer.u_glow.state.notification.NotificationFragmentState

sealed class GalleryFragmentState{
    object Init : GalleryFragmentState()
    object IsShowProgress : GalleryFragmentState()
    data class UpdateGalleryAdapter(var adapter: GalleryAdapter?) : GalleryFragmentState()
}
