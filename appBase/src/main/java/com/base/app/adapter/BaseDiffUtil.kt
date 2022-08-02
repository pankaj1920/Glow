package com.base.app.adapter

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffUtil<T>(private val oldList: MutableList<T>, private val newList: MutableList<T>) : DiffUtil.Callback(){

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]
}