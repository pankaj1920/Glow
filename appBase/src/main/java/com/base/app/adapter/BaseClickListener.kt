package com.base.app.adapter

interface BaseClickListener<T> {
    fun onClickItem(data: T, position: Int)

}