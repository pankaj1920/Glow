package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.OptionViewHolder
import com.developer.u_glow.databinding.InflateOptionsBinding
import com.developer.u_glow.model.dto.OptionData
import com.developer.u_glow.viewmodel.booking.DashboardProViewModel

class OptionAdapter(
    list: MutableList<OptionData>,
    private val viewModel: DashboardProViewModel
) : BaseRecyclerAdapter<OptionData, OptionViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder(
            inflateDataBinding(
                R.layout.inflate_options,
                parent
            ) as InflateOptionsBinding, viewModel
        )
    }
}