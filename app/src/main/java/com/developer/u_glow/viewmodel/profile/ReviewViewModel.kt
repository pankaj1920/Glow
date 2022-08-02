package com.developer.u_glow.viewmodel.profile

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.ReviewAdapter
import com.developer.u_glow.model.dto.ReviewData
import com.developer.u_glow.state.profile.ReviewFragmentState

class ReviewViewModel:BaseViewModel<ReviewFragmentState>() {
    private var reviewList: MutableList<ReviewData>? = null
    private var reviewAdapter: ReviewAdapter? = null

    private var state: ReviewFragmentState = ReviewFragmentState.Init
        set(value) {
            field = value
            publishState(state)
        }
    override fun onInitialized(bundle: Bundle?) {
        initReview()
    }

    private fun initReview() {
        reviewList = ArrayList()
        reviewList?.add(ReviewData("","",""))
        reviewList?.add(ReviewData("","",""))

        reviewAdapter = ReviewAdapter(reviewList as ArrayList<ReviewData>, this)

        state= ReviewFragmentState.UpdateReviewAdapter(reviewAdapter)

    }
}