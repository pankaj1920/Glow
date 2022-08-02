package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateReviewBinding
import com.developer.u_glow.model.dto.ReviewData
import com.developer.u_glow.viewmodel.profile.EditProfileProViewModel
import com.developer.u_glow.viewmodel.profile.ReviewViewModel

class ReviewViewHolder(
    view: InflateReviewBinding,
    private val viewModel:  ReviewViewModel
) : BaseViewHolder<ReviewData, InflateReviewBinding>(view) {
    override fun populateData(data: ReviewData) {
    }

}