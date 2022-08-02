package com.base.app.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.base.app.utils.CodeSnippet

abstract class BaseViewHolder<T, VB : ViewDataBinding> : RecyclerView.ViewHolder {

    var codeSnippet = CodeSnippet(itemView.context)

    open var size: Int = 0

    var data: T? = null
        set(value) {
            field = value
            data?.let { populateData(it) }
        }

    var wholeList: MutableList<T>? = null

    protected lateinit var viewBinding: VB

    open var lastItemPosition = 0

    fun getPreviousItem(): T? {
        return if (adapterPosition != 0)
            wholeList?.get(adapterPosition - 1)
        else null
    }

    constructor(viewDataBinding: VB) : super(viewDataBinding.root) {
        this.viewBinding = viewDataBinding
    }

    constructor(itemView: View) : super(itemView)

    abstract fun populateData(data: T)

}