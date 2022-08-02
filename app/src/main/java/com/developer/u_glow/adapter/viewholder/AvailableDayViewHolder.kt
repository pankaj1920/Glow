package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateAvailableDateBinding
import com.developer.u_glow.model.dto.SettingProData


class AvailableDayViewHolder(var view: InflateAvailableDateBinding) :
    BaseViewHolder<SettingProData, InflateAvailableDateBinding>(view) {
    override fun populateData(data: SettingProData) {

        viewBinding.tvDay.text = data.day

        viewBinding.selectMorning = 0
        viewBinding.selectAfternoon = 0
        viewBinding.selectEvening = 0

        viewBinding.view = this
    }

    fun onClickSelectMorning() {

        if (viewBinding.selectMorning==0){
            viewBinding.selectMorning=1
        }else{
            viewBinding.selectMorning=0
        }
        viewBinding.selectAfternoon=0
        viewBinding.selectEvening=0


    }
    fun onClickSelectAfternoon() {
        viewBinding.selectMorning = 0
        if (viewBinding.selectAfternoon ==0){
            viewBinding.selectAfternoon=1
        }else{
            viewBinding.selectAfternoon=0
        }
        viewBinding.selectEvening=0


    }
    fun onClickSelectEvening() {

        viewBinding.selectMorning = 0
        viewBinding.selectAfternoon=0

        if (viewBinding.selectEvening==0){
            viewBinding.selectEvening=1
        }else{
            viewBinding.selectEvening=0
        }

    }

}