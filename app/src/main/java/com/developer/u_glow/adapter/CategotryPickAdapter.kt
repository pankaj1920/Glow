package com.developer.u_glow.adapter

import android.view.ViewGroup
import android.widget.BaseAdapter
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.CategoryPickViewHolder
import com.developer.u_glow.databinding.InflateCategoryBinding
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.viewmodel.booking.PickCategoryViewModel
import timber.log.Timber

class CategotryPickAdapter(
    var categoryList: MutableList<CategoryData>,
    var viewModel: PickCategoryViewModel
) : BaseRecyclerAdapter<CategoryData, CategoryPickViewHolder>(categoryList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPickViewHolder {
        return CategoryPickViewHolder(
            inflateDataBinding(R.layout.inflate_category, parent) as InflateCategoryBinding,
            viewModel
        )
    }


    fun filterItem(list: MutableList<CategoryData>) {

        categoryList=list
        Timber.d("filteredlist ${list}")
        this.notifyDataSetChanged()
    }
}