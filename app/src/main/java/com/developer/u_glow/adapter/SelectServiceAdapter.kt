package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.SelectServiceViewHolder
import com.developer.u_glow.databinding.InflateSelectServiceBinding
import com.developer.u_glow.model.dto.SelectServiceData
import com.developer.u_glow.viewmodel.profile.SelectServiceViewModel


class SelectServiceAdapter(var list:MutableList<SelectServiceData>,var viewModel: SelectServiceViewModel):
    BaseRecyclerAdapter<SelectServiceData,SelectServiceViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectServiceViewHolder {
        return SelectServiceViewHolder(
            inflateDataBinding(
                R.layout.inflate_select_service,
                parent
            ) as InflateSelectServiceBinding,viewModel)
    }



}