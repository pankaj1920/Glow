package com.developer.u_glow.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.developer.u_glow.R
import com.developer.u_glow.model.dto.PeopleData


class PeopleAdapter(private val context: Context, private val list: MutableList<PeopleData>) : BaseAdapter() {

    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = list.size.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val rootView = LayoutInflater.from(context).inflate(R.layout.inflate_people_spinner, parent, false)
        rootView.findViewById<AppCompatTextView>(R.id.tvTitle).text = list[position].name
        return rootView
    }
}