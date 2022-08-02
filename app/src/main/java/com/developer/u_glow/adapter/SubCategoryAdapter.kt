package com.developer.u_glow.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.CategoryViewHolder
import com.developer.u_glow.databinding.InflateCategoryBinding
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.viewmodel.booking.SelectSubCategoryViewModel
import kotlinx.android.synthetic.main.alert_forgot_password.view.*
import timber.log.Timber

class SubCategoryAdapter(
    var list: MutableList<CategoryData>?=null,
    private val viewModel: SelectSubCategoryViewModel,private var IsLoadMore:Boolean=false
) : BaseRecyclerAdapter<CategoryData, CategoryViewHolder>(list) {
    private var checkedPosition:Int=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            inflateDataBinding(
                R.layout.inflate_category,
                parent
            ) as InflateCategoryBinding, viewModel
        )

    }

    override fun getItemCount(): Int {
        return if(!IsLoadMore && list!!.size >9){
            9
        }else{
            list!!.size
        }
    }

    fun setLoadMore(loadMore:Boolean,listNew: MutableList<CategoryData>){
        IsLoadMore=loadMore
        list?.clear()
        list?.addAll(listNew)
        notifyDataSetChanged()
    }

    fun updateList(searchedList: MutableList<CategoryData>){
        list=searchedList
        notifyDataSetChanged()
        setFilter(searchedList)
    }

    fun setPreviousToTrue(lastPos: Int,context: Context) {
        list!![lastPos].select = true
        notifyDataSetChanged()


    }





}