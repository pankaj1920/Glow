package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.BookingViewHolder
import com.developer.u_glow.databinding.InflateBookingBinding
import com.developer.u_glow.model.dto.BookingData
import com.developer.u_glow.model.dto.OpenBookingData

class BookingAdapter(
    list: MutableList<OpenBookingData>,
    private val isShowEdit: Boolean = false,
    private val type: Int,
    private val viewModel: Any
) : BaseRecyclerAdapter<OpenBookingData, BookingViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(
            inflateDataBinding(
                R.layout.inflate_booking,
                parent
            ) as InflateBookingBinding, isShowEdit, type, viewModel
        )
    }
}