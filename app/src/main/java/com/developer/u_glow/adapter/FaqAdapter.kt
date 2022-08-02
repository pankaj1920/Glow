package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.FaqViewHolder
import com.developer.u_glow.databinding.InflateFaqLayoutBinding
import com.developer.u_glow.model.dto.FaqData


class FaqAdapter(list: MutableList<FaqData>) :
    BaseRecyclerAdapter<FaqData, FaqViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {

        return FaqViewHolder(

            inflateDataBinding(
                R.layout.inflate_faq_layout,
                parent
            ) as InflateFaqLayoutBinding
        )
    }
}