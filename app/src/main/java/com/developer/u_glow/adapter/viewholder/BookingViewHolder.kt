package com.developer.u_glow.adapter.viewholder

import androidx.core.view.isVisible
import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateBookingBinding
import com.developer.u_glow.model.dto.BookingData
import com.developer.u_glow.model.dto.OpenBookingData
import com.developer.u_glow.viewmodel.booking.BookingViewModel
import com.developer.u_glow.viewmodel.booking.OpenGlowViewModel

class BookingViewHolder(
    view: InflateBookingBinding,
    private val isShowEdit: Boolean,
    val type: Int,
    private val viewModel: Any
) : BaseViewHolder<OpenBookingData, InflateBookingBinding>(view) {
    override fun populateData(data: OpenBookingData) {
        viewBinding.isShowEdit = isShowEdit
        var title:String?=null
        viewBinding.type = type
        viewBinding.tvOffers.setOnClickListener {
            when (viewModel) {
                is OpenGlowViewModel -> {
                    viewModel.onClickMakeOffer(adapterPosition, data)
                }

                is BookingViewModel -> {
                    viewModel.onClickGlow(adapterPosition, data)
                }
            }
        }

        data.let {
            viewBinding.tvDate.text = data.date
            viewBinding.tvTime.text = data.time
            viewBinding.tvOffers.text = "${data.offerCount} Offers"
            viewBinding.tvBudget.text="€${data.amount.toString()}"
            viewBinding.tvLocation.text=data.address


            viewBinding.tvFlexibility.isVisible = data.isFlexible!!
          data.services?.forEach {
              if (title != null) {
                  title = title + "," + it.name
              } else {
                  title = it.name
              }
          }
            viewBinding.tvHeading.text=title
        }

    }
}