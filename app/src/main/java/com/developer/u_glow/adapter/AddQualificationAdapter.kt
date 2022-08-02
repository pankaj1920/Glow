package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.AddQualificationViewHolder
import com.developer.u_glow.databinding.InflateAddQualificationBinding
import com.developer.u_glow.model.dto.AddQualificationData


class AddQualificationAdapter(list: MutableList<AddQualificationData>) :
    BaseRecyclerAdapter<AddQualificationData, AddQualificationViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddQualificationViewHolder {

        return AddQualificationViewHolder(

            inflateDataBinding(
                R.layout.inflate_add_qualification,
                parent
            ) as InflateAddQualificationBinding
        )
    }
}