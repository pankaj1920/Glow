package com.developer.u_glow.view.fragment.authenticate

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentRegisterTwoBinding
import com.developer.u_glow.viewmodel.authenticate.RegisterTwoViewModel

class RegisterTwoFragment(override val layoutId: Int = R.layout.fragment_register_two) :
    BaseFragment<RegisterTwoViewModel, FragmentRegisterTwoBinding>() {

    override val mViewModel: RegisterTwoViewModel by viewModels()

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            /*when (it) {

                else -> {
                }
            }*/
        })
    }

    override fun onFragmentCreated() {
    }
}