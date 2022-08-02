package com.developer.u_glow.adapter.viewholder

import android.app.AlertDialog
import com.base.app.adapter.BaseViewHolder
import com.base.app.utils.loadCircleImage
import com.base.app.utils.loadUrl
import com.developer.u_glow.R
import com.developer.u_glow.databinding.InflateCategoryBinding
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.viewmodel.booking.DashboardViewModel
import com.developer.u_glow.viewmodel.booking.PickCategoryViewModel
import com.developer.u_glow.viewmodel.booking.SelectSubCategoryViewModel
import kotlinx.android.synthetic.main.alert_forgot_password.view.*
import timber.log.Timber

class SubCategoryViewHolder(
    view: InflateCategoryBinding, private val viewModel: SelectSubCategoryViewModel
) : BaseViewHolder<CategoryData, InflateCategoryBinding>(view) {


    override fun populateData(data: CategoryData) {

        viewBinding.root.setOnClickListener {

            viewModel.onClickSubCategory(adapterPosition, data,itemView.context)
//            when (viewModel) {
//                is DashboardViewModel -> {
//                    viewModel.onClickCategory(adapterPosition, data)
//                }
//
//                is SelectSubCategoryViewModel -> {
//                    viewModel.onClickSubCategory(adapterPosition, data)
//                }
//                is PickCategoryViewModel -> {
//                    viewModel.onClickSubCategory(adapterPosition, data)
//                }
//            }


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