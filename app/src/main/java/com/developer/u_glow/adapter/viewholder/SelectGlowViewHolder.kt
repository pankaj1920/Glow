package com.developer.u_glow.adapter.viewholder

import androidx.core.content.ContextCompat
import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateSelectYourGlowBinding
import com.developer.u_glow.model.dto.SelectGlowData
import com.developer.u_glow.model.dto.ServiceData
import com.developer.u_glow.viewmodel.booking.SelectGlowViewModel

class SelectGlowViewHolder(view: InflateSelectYourGlowBinding, var viewModel: SelectGlowViewModel) :
    BaseViewHolder<ServiceData, InflateSelectYourGlowBinding>(view) {
    override fun populateData(data: ServiceData) {

        viewBinding.selected = true
        viewBinding.view = this

        viewBinding.root.setOnClickListener {
            viewBinding.selected = !viewBinding.selected
            viewModel.onClickAddData(data, itemView.context)

        }

        viewBinding.tvHair.text=data.name
    }

}