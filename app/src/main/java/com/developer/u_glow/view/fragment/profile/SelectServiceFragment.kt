package com.developer.u_glow.view.fragment.profile

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentSelectServiceBinding
import com.developer.u_glow.state.profile.SelectServiceFragmentState
import com.developer.u_glow.viewmodel.profile.SelectServiceViewModel


class SelectServiceFragment : BaseFragment<SelectServiceViewModel, FragmentSelectServiceBinding>() {
    override val mViewModel: SelectServiceViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_select_service

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is SelectServiceFragmentState.UpdateSelectServiceAdapter -> {
                    mViewBinding.rvSelectService.adapter = it.adapter
                }

                is SelectServiceFragmentState.IsShowProgress -> {
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
        findNavController().navigateTo(R.id.nav_add_qualification, mViewModel.bundleInstance)
        /*val action= SelectServiceFragmentDirections.actionSelectServiceFragment3ToSettingPassportFragment()
        findNavController().navigate(action)*/
    }


}