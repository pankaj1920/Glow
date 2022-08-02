package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateAddServiceBinding
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.model.dto.Services
import com.developer.u_glow.viewmodel.booking.SelectGlowViewModel

class AddServiceViewHolder(view: InflateAddServiceBinding,var viewModel: SelectGlowViewModel) :
    BaseViewHolder<Services, InflateAddServiceBinding>(view) {

    override fun populateData(data: Services) {


        viewBinding.listener=viewModel
        viewBinding.position=adapterPosition


      viewBinding.tvHair.text=data.name

    }
}