package com.developer.u_glow.adapter


import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.AddServiceViewHolder
import com.developer.u_glow.databinding.InflateAddServiceBinding
import com.developer.u_glow.model.dto.PostGlowData

import com.developer.u_glow.model.dto.SelectGlowData
import com.developer.u_glow.model.dto.ServiceData
import com.developer.u_glow.model.dto.Services
import com.developer.u_glow.viewmodel.booking.SelectGlowViewModel

class AddServiceAdapter(
   var  list: MutableList<Services>, private val viewModel: SelectGlowViewModel
) : BaseRecyclerAdapter<Services, AddServiceViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddServiceViewHolder {
        return AddServiceViewHolder(
            inflateDataBinding(
                R.layout.inflate_add_service,
                parent
            ) as InflateAddServiceBinding
        ,viewModel)

    }




}