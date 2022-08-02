package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.FilterViewHolder
import com.developer.u_glow.databinding.InflateFilterBinding
import com.developer.u_glow.model.dto.FilterData

class FilterAdapter(
    list: MutableList<FilterData>
) : BaseRecyclerAdapter<FilterData, FilterViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(
            inflateDataBinding(
                R.layout.inflate_filter,
                parent
            ) as InflateFilterBinding
        )
    }
}