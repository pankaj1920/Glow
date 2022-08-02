package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.base.app.utils.loadUrl
import com.developer.u_glow.databinding.InflateCategoryBinding
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.viewmodel.booking.DashboardViewModel
import com.developer.u_glow.viewmodel.booking.PickCategoryViewModel
import com.developer.u_glow.viewmodel.booking.SelectSubCategoryViewModel

class CategoryPickViewHolder(view: InflateCategoryBinding, var viewModel: PickCategoryViewModel) :
    BaseViewHolder<CategoryData, InflateCategoryBinding>(view) {
    override fun populateData(data: CategoryData) {
        var selectedItemPos = -1
        var previousPos = -1
        var select: Boolean = false
        viewBinding.root.setOnClickListener {

            if (previousPos == -1) {
                previousPos = adapterPosition
            } else {
                previousPos = previousPos
            }
//            viewModel.onClickSubCategory(adapterPosition, data, previousPos)

        }


        if (data.select) data.greenImage?.let {
            viewBinding.ivCategory.loadUrl(data.greenImage)
        } else {
            viewBinding.ivCategory.loadUrl(data.image)

        }
        data.name?.let {
            viewBinding.tvCategory.text = it
        }

    }
}