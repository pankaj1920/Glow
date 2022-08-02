package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.base.app.utils.loadCircleImage
import com.base.app.utils.loadUrl
import com.developer.u_glow.R
import com.developer.u_glow.databinding.InflateCategoryBinding
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.viewmodel.booking.DashboardViewModel
import com.developer.u_glow.viewmodel.booking.PickCategoryViewModel
import com.developer.u_glow.viewmodel.booking.SelectSubCategoryViewModel
import timber.log.Timber

class CategoryViewHolder(
    view: InflateCategoryBinding, private val viewModel: Any? = null
) : BaseViewHolder<CategoryData, InflateCategoryBinding>(view) {


    override fun populateData(data: CategoryData) {

        viewBinding.root.setOnClickListener {

            when (viewModel) {
                is DashboardViewModel -> {
                    viewModel.onClickCategory(adapterPosition, data)
                }

                is SelectSubCategoryViewModel -> {
                    viewModel.onClickSubCategory(adapterPosition, data,itemView.context)
                }
                is PickCategoryViewModel -> {
                    viewModel.onClickSubCategory(adapterPosition, data)
                }
            }


        }

        if (data.select) data.greenImage?.let {
            viewBinding.ivCategory.loadUrl(data.greenImage)
        } else
            data.image?.let {
                viewBinding.ivCategory.loadUrl(data.image)
            }
        data.name?.let {
            viewBinding.tvCategory.text = it
        }

    }

}