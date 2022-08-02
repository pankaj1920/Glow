package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.SelectGlowViewHolder
import com.developer.u_glow.databinding.InflateSelectYourGlowBinding
import com.developer.u_glow.model.dto.SelectGlowData
import com.developer.u_glow.model.dto.ServiceData
import com.developer.u_glow.viewmodel.booking.SelectGlowViewModel

class SelectGlowAdapter(
    list: MutableList<ServiceData>,var viewModel: SelectGlowViewModel

    ) : BaseRecyclerAdapter<ServiceData, SelectGlowViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectGlowViewHolder {
        return SelectGlowViewHolder(
            inflateDataBinding(
                R.layout.inflate_select_your_glow,
                parent
            ) as InflateSelectYourGlowBinding,viewModel
        )


    }



}