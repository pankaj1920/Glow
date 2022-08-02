package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.AvailableDayViewHolder
import com.developer.u_glow.databinding.InflateAvailableDateBinding
import com.developer.u_glow.model.dto.SettingProData

class AvailableDayAdapter (
    list: MutableList<SettingProData>
) : BaseRecyclerAdapter<SettingProData, AvailableDayViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableDayViewHolder {
        return AvailableDayViewHolder(
            inflateDataBinding(
                R.layout.inflate_available_date,
                parent
            ) as InflateAvailableDateBinding
        )
    }
}