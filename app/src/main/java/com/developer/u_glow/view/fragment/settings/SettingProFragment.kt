package com.developer.u_glow.view.fragment.settings


import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentSettingProBinding
import com.developer.u_glow.state.settings.SettingProFragmentState
import com.developer.u_glow.viewmodel.settings.SettingProViewModel

class SettingProFragment : BaseFragment<SettingProViewModel,FragmentSettingProBinding>(){
    override val mViewModel: SettingProViewModel by viewModels()
    override val layoutId: Int
        get() =R.layout.fragment_setting_pro

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {
        mViewBinding.view=this

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is SettingProFragmentState.UpdateAvailableDateAdapter -> {
                    mViewBinding.rvDay.adapter= it.adapter
                }

                else-> {}
            }
        })

    }

    fun onClickNavigation(){
        findNavController().navigateTo(R.id.nav_setting_passport_pro_fragment)
//        val action= SettingProFragmentDirections.actionSettingProFragment2ToGalleryFragment()
//        findNavController().navigate(action)
    }

}