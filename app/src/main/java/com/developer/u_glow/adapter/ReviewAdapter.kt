package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.ReviewViewHolder
import com.developer.u_glow.databinding.InflateReviewBinding
import com.developer.u_glow.model.dto.ReviewData
import com.developer.u_glow.viewmodel.profile.ReviewViewModel


class ReviewAdapter(
    list: MutableList<ReviewData>,
    private val viewModel: ReviewViewModel
) : BaseRecyclerAdapter<ReviewData, ReviewViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(

            inflateDataBinding(R.layout.inflate_review,parent) as InflateReviewBinding, viewModel
        )
    }

}