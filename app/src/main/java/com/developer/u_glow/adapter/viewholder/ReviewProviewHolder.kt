package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateProReviewBinding
import com.developer.u_glow.databinding.InflateReviewBinding
import com.developer.u_glow.model.dto.ReviewData
import com.developer.u_glow.viewmodel.profile.EditProfileProViewModel


class ReviewProviewHolder (
    view: InflateProReviewBinding,
    private val viewModel: EditProfileProViewModel
) : BaseViewHolder<ReviewData, InflateProReviewBinding>(view) {
    override fun populateData(data: ReviewData) {

    }

}