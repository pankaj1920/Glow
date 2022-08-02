package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.SelectSubServiceListViewHolder
import com.developer.u_glow.databinding.InflateSelectSubServiceListBinding
import com.developer.u_glow.model.dto.SelectSubServiceData
import com.developer.u_glow.viewmodel.profile.SelectServiceViewModel

class SelectSubServiceListAdapter(var list:MutableList<SelectSubServiceData>, var viewModel: SelectServiceViewModel):
    BaseRecyclerAdapter<SelectSubServiceData, SelectSubServiceListViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSubServiceListViewHolder {
        return SelectSubServiceListViewHolder(
            inflateDataBinding(
                R.layout.inflate_select_sub_service_list,
                parent
            ) as InflateSelectSubServiceListBinding
            ,viewModel)
    }


}