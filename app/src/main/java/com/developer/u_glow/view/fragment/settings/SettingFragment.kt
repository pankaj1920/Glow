package com.developer.u_glow.view.fragment.settings

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentSettingBinding
import com.developer.u_glow.viewmodel.settings.SettingViewModel


class SettingFragment : BaseFragment<SettingViewModel,FragmentSettingBinding>() {
    override val mViewModel: SettingViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_setting

    override fun subscribeObservers() {


    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onFragmentCreated() {
        mViewBinding.view=this
        mViewBinding.cardViewMessage.setOnTouchListener { p0, p1 ->
            if (p0?.id == R.id.cardViewMessage) {
                p0.parent.requestDisallowInterceptTouchEvent(true)
            }
            false
        }

    }

    fun onClickNavigation(){

//        val action=SettingFragmentDirections.actionSettingFragmentToYourProfileFragment()
//        findNavController().navigate(action)
    }



}