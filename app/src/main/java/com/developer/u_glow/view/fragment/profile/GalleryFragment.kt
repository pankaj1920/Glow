package com.developer.u_glow.view.fragment.profile


import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateNew
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentGalleryBinding
import com.developer.u_glow.state.profile.GalleryFragmentState
import com.developer.u_glow.viewmodel.profile.GalleryViewModel

class GalleryFragment : BaseFragment<GalleryViewModel, FragmentGalleryBinding>() {
    override val mViewModel: GalleryViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_gallery

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {

            when (it) {
                is GalleryFragmentState.UpdateGalleryAdapter -> {
                    mViewBinding.rvGallery.adapter = it.adapter
                }

                is GalleryFragmentState.IsShowProgress -> {
                    mViewBinding.grpEdit.visibility = View.VISIBLE
                }

                else -> {
                }
            }

        })
    }

    override fun onFragmentCreated() {

        mViewBinding.view = this


    }

    fun onClickNavigation() {
        findNavController().navigateNew(R.id.nav_dashboard_pro_fragment, R.id.nav_graph_pro)
    }

}