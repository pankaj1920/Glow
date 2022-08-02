package com.developer.u_glow.adapter.viewholder


import com.base.app.adapter.BaseViewHolder
import com.base.app.utils.loadCurvedImage
import com.base.app.utils.loadUrl
import com.developer.u_glow.databinding.InflateGalleryBinding
import com.developer.u_glow.model.dto.GalleryData
import com.developer.u_glow.viewmodel.booking.DetailsViewModel
import com.developer.u_glow.viewmodel.profile.GalleryViewModel
import timber.log.Timber


class GalleryViewHolder(
    view: InflateGalleryBinding, var viewModel:Any?=null
) : BaseViewHolder<GalleryData, InflateGalleryBinding>(view) {
    override fun populateData(data: GalleryData) {


            when (viewModel) {
                is DetailsViewModel -> {
                    viewBinding.ivGallery.loadCurvedImage(data.galleryImage.toString(), 8f)
                    viewBinding.listener = viewModel as DetailsViewModel
                    viewBinding.position = adapterPosition
                }
            }
    }
}