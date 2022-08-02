package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateFaqLayoutBinding
import com.developer.u_glow.model.dto.FaqData

class FaqViewHolder(  view: InflateFaqLayoutBinding):BaseViewHolder<FaqData,InflateFaqLayoutBinding>(view) {
    override fun populateData(data: FaqData) {

        viewBinding.selected=false

        viewBinding.view=this

    }


    fun onClickSelect(){
        viewBinding.selected=!viewBinding.selected
    }
}