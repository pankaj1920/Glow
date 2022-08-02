package com.developer.u_glow.view.fragment.profile


import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentAddQualificationBinding
import com.developer.u_glow.state.profile.AddQualificationState
import com.developer.u_glow.state.profile.ProfileState
import com.developer.u_glow.viewmodel.profile.AddQualificationViewModel


class AddQualificationFragment :
    BaseFragment<AddQualificationViewModel, FragmentAddQualificationBinding>() {
    override val mViewModel: AddQualificationViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_add_qualification

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is AddQualificationState.UpdateAddQualificationAdapter -> {
                    mViewBinding.rvAddQualification.adapter = it.adapter
                }

                is AddQualificationState.IsShowProgress -> {
                    mViewBinding.grpEdit.visibility = View.VISIBLE
                }

                else -> {
                }
            }
        })
    }

    override fun onFragmentCreated() {

        mViewBinding.listener = mViewModel
        mViewBinding.view = this
    }

    fun onClickNavigation() {
        findNavController().navigateTo(R.id.nav_gallery_fragment, mViewModel.bundleInstance)
    }
}