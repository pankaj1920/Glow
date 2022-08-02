package com.developer.u_glow.view.fragment.profile

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.loadCurvedImage
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentEditProfileProBinding
import com.developer.u_glow.state.profile.EditProfileProFragmenState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.viewmodel.profile.EditProfileProViewModel


class EditProfileProFragment :
    BaseFragment<EditProfileProViewModel, FragmentEditProfileProBinding>() {
    override val mViewModel: EditProfileProViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_edit_profile_pro

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is EditProfileProFragmenState.UpdateReviewProAdapter -> {
                    mViewBinding.rvEditProfile.adapter = it.adapter
                }

                else -> {
                }
            }
        })
    }

    override fun onFragmentCreated() {

        mViewBinding.view = this

        mViewBinding.ivGallery.loadCurvedImage(
            "https://docs.imagga.com/static/images/docs/sample/japan-605234_1280.jpg",
            resources.getDimension(R.dimen._20sdp)
        )
    }

    fun onClickDetails() {
        findNavController().navigateTo(R.id.nav_profile_fragment)
    }

    fun onClickQualification() {
        findNavController().navigateTo(R.id.nav_add_qualification)
    }

    fun onClickGallery() {
        findNavController().navigateTo(R.id.nav_gallery_fragment)
    }

}