package com.developer.u_glow.view.fragment.booking

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateNew
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentGlowDeclineBinding
import com.developer.u_glow.viewmodel.booking.GlowAcceptedViewModel


class GlowDeclineFragment : BaseFragment<GlowAcceptedViewModel,FragmentGlowDeclineBinding >() {
    override val mViewModel: GlowAcceptedViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_glow_decline

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {

        mViewBinding.view=this
    }


    fun onClickNavigation(){
        findNavController().navigateNew(R.id.nav_dashboard_pro_fragment, R.id.nav_graph_pro)
//        val action =GlowDeclineFragmentDirections.actionGlowDeclineFragmentToNotificationFragment()
//        findNavController().navigate(action)
    }
}