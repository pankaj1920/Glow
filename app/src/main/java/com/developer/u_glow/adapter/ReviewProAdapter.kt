package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.ReviewProviewHolder
import com.developer.u_glow.databinding.InflateProReviewBinding
import com.developer.u_glow.databinding.InflateReviewBinding
import com.developer.u_glow.model.dto.ReviewData
import com.developer.u_glow.viewmodel.profile.EditProfileProViewModel


class ReviewProAdapter (
    list: MutableList<ReviewData>,
    private val viewModel: EditProfileProViewModel
) : BaseRecyclerAdapter<ReviewData, ReviewProviewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewProviewHolder {
        return ReviewProviewHolder(

            inflateDataBinding(R.layout.inflate_pro_review,parent) as InflateProReviewBinding, viewModel
        )
    }

}