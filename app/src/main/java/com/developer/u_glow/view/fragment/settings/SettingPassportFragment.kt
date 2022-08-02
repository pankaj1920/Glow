package com.developer.u_glow.view.fragment.settings


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateNew
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentSettingPassportBinding
import com.developer.u_glow.viewmodel.settings.SettingPassportViewModel

class SettingPassportFragment :
    BaseFragment<SettingPassportViewModel, FragmentSettingPassportBinding>() {
    override val mViewModel: SettingPassportViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_setting_passport

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {
        mViewBinding.view = this
        mViewBinding.selectedPassport = true
        mViewBinding.selectedAddress = true
    }

    fun onClickNavigation() {
        findNavController().navigateNew(R.id.nav_dashboard_pro_fragment, R.id.nav_graph_pro)
    }

    fun onClickChangeColor() {
        mViewBinding.selectedPassport = !mViewBinding.selectedPassport
    }

    fun onClickChangeAddressColor() {
        mViewBinding.selectedAddress = !mViewBinding.selectedAddress
    }
}