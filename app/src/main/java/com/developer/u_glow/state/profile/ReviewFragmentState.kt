package com.developer.u_glow.state.profile

import com.developer.u_glow.adapter.ReviewAdapter

sealed class ReviewFragmentState{

    object Init : ReviewFragmentState()
    data class UpdateReviewAdapter(var adapter: ReviewAdapter?) : ReviewFragmentState()

}
