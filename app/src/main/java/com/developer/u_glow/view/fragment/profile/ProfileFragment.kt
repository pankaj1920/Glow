package com.developer.u_glow.view.fragment.profile

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentProfileBinding
import com.developer.u_glow.state.profile.ProfileState
import com.developer.u_glow.viewmodel.profile.ProfileViewModel


class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override val mViewModel: ProfileViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_profile

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is ProfileState.IsShowProgress -> {
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

    fun onClickNext() {
        findNavController().navigateTo(R.id.nav_service_fragment, mViewModel.bundleInstance)
    }

}