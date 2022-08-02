package com.developer.u_glow.adapter.viewholder

import androidx.core.content.ContextCompat
import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateOptionsBinding
import com.developer.u_glow.model.dto.OptionData
import com.developer.u_glow.viewmodel.booking.DashboardProViewModel

class OptionViewHolder(
    view: InflateOptionsBinding,
    private val viewModel: DashboardProViewModel
) : BaseViewHolder<OptionData, InflateOptionsBinding>(view) {
    override fun populateData(data: OptionData) {
        viewBinding.pos = adapterPosition
        viewBinding.viewModel = viewModel
        data.color?.let {
            viewBinding.card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, it))
        }
        viewBinding.tvMenu.text = data.title?.let { itemView.context.resources.getString(it) }
    }
}