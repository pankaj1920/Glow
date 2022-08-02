package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateSelectSubServiceListBinding
import com.developer.u_glow.model.dto.SelectSubServiceData
import com.developer.u_glow.viewmodel.profile.SelectServiceViewModel

class SelectSubServiceListViewHolder(
    var view: InflateSelectSubServiceListBinding,
    val viewModel: SelectServiceViewModel
) :
    BaseViewHolder<SelectSubServiceData, InflateSelectSubServiceListBinding>(view) {
    override fun populateData(data: SelectSubServiceData) {

        viewBinding.select = true
        viewBinding.view = this

    }

    fun onClickSelect() {
        viewBinding.select = !viewBinding.select
    }
}