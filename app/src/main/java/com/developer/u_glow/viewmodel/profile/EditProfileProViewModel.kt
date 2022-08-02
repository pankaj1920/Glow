package com.developer.u_glow.viewmodel.profile

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.NotificationAdapter
import com.developer.u_glow.adapter.ReviewProAdapter
import com.developer.u_glow.model.dto.NotificationData
import com.developer.u_glow.model.dto.ReviewData
import com.developer.u_glow.state.notification.NotificationFragmentState
import com.developer.u_glow.state.profile.EditProfileProFragmenState

class EditProfileProViewModel: BaseViewModel<EditProfileProFragmenState>() {

    private var ReviewList: MutableList<ReviewData>? = null
    private var ReviewProAdapter: ReviewProAdapter? = null

    private var state: EditProfileProFragmenState = EditProfileProFragmenState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {

        initProRreview()
    }

    private fun initProRreview() {
        ReviewList = ArrayList()
        ReviewList?.add(ReviewData())
        ReviewList?.add(ReviewData())
        ReviewList?.add(ReviewData())
        ReviewProAdapter = ReviewProAdapter(ReviewList as ArrayList<ReviewData>,this)
        state=EditProfileProFragmenState.UpdateReviewProAdapter(ReviewProAdapter)
    }


}