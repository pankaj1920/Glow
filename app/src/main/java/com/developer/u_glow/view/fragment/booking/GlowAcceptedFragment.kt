package com.developer.u_glow.view.fragment.booking

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentGlowAcceptedBinding
import com.developer.u_glow.viewmodel.booking.GlowAcceptedViewModel


class GlowAcceptedFragment : BaseFragment<GlowAcceptedViewModel,FragmentGlowAcceptedBinding>() {
    override val mViewModel: GlowAcceptedViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_glow_accepted

    override fun subscribeObservers() {


    }

    override fun onFragmentCreated() {

        mViewBinding.view=this

    }

    fun onClickNavigation(){
        findNavController().navigateTo(R.id.nav_glow_decline_fragment)
//        val action =GlowAcceptedFragmentDirections.actionGlowAcceptedFragmentToGlowDeclineFragment()
//        findNavController().navigate(action)
    }

}