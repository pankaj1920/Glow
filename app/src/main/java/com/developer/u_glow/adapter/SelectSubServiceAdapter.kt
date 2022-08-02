package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.SelectSubServiceViewHolder
import com.developer.u_glow.databinding.InflateSelectSubServicesBinding
import com.developer.u_glow.model.dto.SelectSubServiceData
import com.developer.u_glow.viewmodel.profile.SelectServiceViewModel

class SelectSubServiceAdapter(var list:MutableList<SelectSubServiceData>,var viewModel: SelectServiceViewModel):
    BaseRecyclerAdapter<SelectSubServiceData, SelectSubServiceViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSubServiceViewHolder {
        return SelectSubServiceViewHolder(
            inflateDataBinding(
                R.layout.inflate_select_sub_services,
                parent
            ) as InflateSelectSubServicesBinding
        ,viewModel)
    }
}