package com.developer.u_glow.view.fragment.authenticate

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentRegisterThreeBinding
import com.developer.u_glow.util.Constants
import com.developer.u_glow.viewmodel.authenticate.RegisterThreeViewModel
import com.developer.u_glow.viewmodel.authenticate.RegisterTwoViewModel
import kotlinx.android.synthetic.main.alert_forgot_password.view.*

class RegisterThreeFragment(override val layoutId: Int = R.layout.fragment_register_three) :
    BaseFragment<RegisterThreeViewModel, FragmentRegisterThreeBinding>() {

    override val mViewModel: RegisterThreeViewModel by viewModels()

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            /*when (it) {

                else -> {
                }
            }*/
        })
    }

    override fun onFragmentCreated() {
        mViewBinding.view = this
    }

    fun onClickDashboard() {
        sharedPreferences.isCustomer = mViewModel.isCustomer
       navigateTo(null, Constants.Activity.home)
    }

    fun onClickConfirmAccount(){
        val alert = createAlert(R.layout.alert_verification)
        alert.first.btnDone.setOnClickListener { alert.second.dismiss() }
        alert.second.show()
    }
}