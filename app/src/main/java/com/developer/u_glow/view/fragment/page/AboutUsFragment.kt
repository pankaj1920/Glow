package com.developer.u_glow.view.fragment.page

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateNew
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentAboutUsBinding
import com.developer.u_glow.viewmodel.page.AboutUsViewModel

class AboutUsFragment : BaseFragment<AboutUsViewModel, FragmentAboutUsBinding>() {
    override val mViewModel: AboutUsViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_about_us

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {

        mViewBinding.view = this
    }

    fun onClickNavigation() {
        if (sharedPreferences.isCustomer!!) findNavController().navigateNew(
            R.id.nav_dashboard_fragment,
            R.id.nav_graph
        )
        else
            findNavController().navigateNew(R.id.nav_dashboard_pro_fragment, R.id.nav_graph_pro)

    }

}