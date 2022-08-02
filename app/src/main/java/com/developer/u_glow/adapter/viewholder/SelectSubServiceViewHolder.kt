package com.developer.u_glow.adapter.viewholder


import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateSelectSubServicesBinding
import com.developer.u_glow.model.dto.SelectSubServiceData
import com.developer.u_glow.viewmodel.profile.SelectServiceViewModel

class SelectSubServiceViewHolder(
    var view: InflateSelectSubServicesBinding,
    val viewModel: SelectServiceViewModel
) :
    BaseViewHolder<SelectSubServiceData, InflateSelectSubServicesBinding>(view) {
    override fun populateData(data: SelectSubServiceData) {
        setAdapter()
    }

    private fun setAdapter() {
        viewBinding.listener = this
        viewBinding.position=adapterPosition
        viewBinding.select=true
        viewBinding.rvSelectSubServiceList.adapter = viewModel.initializeSelectSubServiceList()

    }


    fun showAndHide() {
        viewBinding.select = !viewBinding.select
    }


}