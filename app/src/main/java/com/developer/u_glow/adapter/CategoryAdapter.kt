package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.CategoryViewHolder
import com.developer.u_glow.databinding.InflateCategoryBinding
import com.developer.u_glow.model.dto.CategoryData
import timber.log.Timber

class CategoryAdapter(
    var list: MutableList<CategoryData>? = null,
    private val viewModel: Any? = null, private var IsLoadMore: Boolean = false
) : BaseRecyclerAdapter<CategoryData, CategoryViewHolder>(list) {
    private var checkedPosition: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            inflateDataBinding(
                R.layout.inflate_category,
                parent
            ) as InflateCategoryBinding, viewModel
        )


    }

    override fun getItemCount(): Int {
        return if (!IsLoadMore && list!!.size > 9) {
            9
        } else {
            list!!.size
        }
    }

    fun setLoadMore(loadMore: Boolean, listNew: MutableList<CategoryData>) {
        IsLoadMore = loadMore
        list = listNew
        notifyDataSetChanged()
    }

    fun updateList(searchedList: MutableList<CategoryData>) {
        list = searchedList
        notifyDataSetChanged()
        setFilter(searchedList)
    }

    fun setPreviousToTrue(lastPos: Int) {
        list!![lastPos].select = false
        notifyDataSetChanged()
    }
}