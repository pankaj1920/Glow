package com.developer.u_glow.view.fragment.booking

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateNew
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentGlowPostedBinding
import com.developer.u_glow.viewmodel.booking.GlowPostedViewModel

class GlowPostedFragment : BaseFragment<GlowPostedViewModel, FragmentGlowPostedBinding>() {
    override val mViewModel: GlowPostedViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_glow_posted

    override fun subscribeObservers() {
    }

    override fun onFragmentCreated() {
      mViewBinding.view=this
    }

    fun onClickNavigation() {
        findNavController().navigateNew(R.id.nav_dashboard_fragment, R.id.nav_graph)
    }

}