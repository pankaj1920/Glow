package com.developer.u_glow.viewmodel.profile

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.R
import com.developer.u_glow.adapter.GalleryAdapter
import com.developer.u_glow.model.dto.GalleryData
import com.developer.u_glow.state.profile.GalleryFragmentState
import com.developer.u_glow.state.profile.ProfileState
import com.developer.u_glow.util.Constants

class GalleryViewModel: BaseViewModel<GalleryFragmentState>() {
    private var galleryList: MutableList<GalleryData>? = null
    private var galleryAdapter: GalleryAdapter? = null
    var bundleInstance: Bundle? = null

    private var state: GalleryFragmentState = GalleryFragmentState.Init
        set(value) {
            field = value
            publishState(state)
        }
    override fun onInitialized(bundle: Bundle?) {
        if (bundle != null){
            bundleInstance = bundle
            if (bundle.containsKey(Constants.BundleKey.isShowProgress))
                state = GalleryFragmentState.IsShowProgress
        }
        initGallery()

    }

    private fun initGallery() {
        galleryList = ArrayList()
//        galleryList?.add(GalleryData(R.drawable.gallery_one))
//        galleryList?.add(GalleryData(R.drawable.gallery_one))
//        galleryList?.add(GalleryData(R.drawable.gallery_three))
//        galleryList?.add(GalleryData(R.drawable.gallery_three))
//        galleryList?.add(GalleryData(R.drawable.gallery_two))
//        galleryList?.add(GalleryData(R.drawable.gallery_two))
//        galleryList?.add(GalleryData(R.drawable.gallery_two))
        galleryAdapter = GalleryAdapter(galleryList as ArrayList<GalleryData>,this)
        state=GalleryFragmentState.UpdateGalleryAdapter(galleryAdapter)
    }

    fun deleteItem(position:Int){
        galleryAdapter?.removeItem(position)
        galleryAdapter?.notifyDataSetChanged()
    }
}