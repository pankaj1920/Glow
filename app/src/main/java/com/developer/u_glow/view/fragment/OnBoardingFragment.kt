package com.developer.u_glow.view.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentOnboardingBinding
import com.developer.u_glow.state.onboarding.OnBoardFragmentState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.viewmodel.onboarding.OnBoardFragmentViewModel

class OnBoardingFragment(override val layoutId: Int = R.layout.fragment_onboarding) :
    BaseFragment<OnBoardFragmentViewModel, FragmentOnboardingBinding>() {

    override val mViewModel: OnBoardFragmentViewModel by viewModels()

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is OnBoardFragmentState.UpdateOnBoardData -> {
                    mViewBinding.pos = it.pos

                }

                else-> {}
            }
        })
    }

    override fun onFragmentCreated() {

    }
}