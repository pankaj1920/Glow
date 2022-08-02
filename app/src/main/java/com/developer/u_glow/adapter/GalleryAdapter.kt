package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.GalleryViewHolder
import com.developer.u_glow.databinding.InflateGalleryBinding
import com.developer.u_glow.model.dto.GalleryData
import com.developer.u_glow.viewmodel.profile.GalleryViewModel

class GalleryAdapter (
    list: MutableList<GalleryData>,var viewModel:Any?=null
) : BaseRecyclerAdapter<GalleryData, GalleryViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            inflateDataBinding(
                R.layout.inflate_gallery,
                parent
            ) as InflateGalleryBinding
        ,viewModel)
    }
}