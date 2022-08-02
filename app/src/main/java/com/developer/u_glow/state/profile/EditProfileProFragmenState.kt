package com.developer.u_glow.state.profile

import com.developer.u_glow.adapter.ReviewProAdapter

sealed class EditProfileProFragmenState{

    object Init : EditProfileProFragmenState()
    data class UpdateReviewProAdapter(var adapter: ReviewProAdapter?) : EditProfileProFragmenState()
}
