package com.developer.u_glow.adapter.viewholder


import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateSelectServiceBinding
import com.developer.u_glow.model.dto.SelectServiceData

import com.developer.u_glow.viewmodel.profile.SelectServiceViewModel


class SelectServiceViewHolder( var view: InflateSelectServiceBinding,var viewModel: SelectServiceViewModel) :
    BaseViewHolder<SelectServiceData, InflateSelectServiceBinding>(view) {
    override fun populateData(data: SelectServiceData) {

        setAdapter()
        viewBinding.listener=this

        viewBinding.select=true

    }


   private fun setAdapter(){

        viewBinding.rvSelectSubService.adapter=viewModel.initializeSelectSubService()
    }


    fun showAndHide(position:Int){
        viewBinding.position=position
        viewBinding.select=!viewBinding.select
    }



}