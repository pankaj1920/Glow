package com.developer.u_glow.view.activity.authenticate

import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.base.app.utils.navigateNew
import com.base.app.utils.navigateTo
import com.base.app.view.BaseActivity
import com.developer.u_glow.R
import com.developer.u_glow.databinding.ActivityRegisterBinding
import com.developer.u_glow.util.Constants
import com.developer.u_glow.viewmodel.authenticate.RegisterViewModel

class RegisterActivity(override val layoutId: Int = R.layout.activity_register) :
    BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override val mViewModel: RegisterViewModel by viewModels()
    private var navController: NavController? = null

    override fun onInitialize() {

        navController = findNavController(R.id.fvRegister)
        mViewBinding.view = this
        mViewBinding.position = Constants.Count.zero
        navController?.navigateTo(R.id.nav_register_one_fragment,  mViewModel.bundleInstance)

    }


    override fun subscribeObservers() {
         mViewModel.stateObserver.observe(this) {
             when (it) {

             }
         }
    }

    fun onClickStep(position: Int) {
        if (mViewBinding.position != position) {
            mViewBinding.position = position
            mViewBinding.btnProceed.visibility =
                if (Constants.Count.two == position) View.GONE else View.VISIBLE
            when (position) {
                Constants.Count.one -> navController?.navigateNew(R.id.nav_register_two_fragment, R.id.nav_register)
                Constants.Count.two -> navController?.navigateNew(R.id.nav_register_three_fragment, R.id.nav_graph, mViewModel.bundleInstance)
                else -> navController?.navigateNew(R.id.nav_register_one_fragment, R.id.nav_graph,  mViewModel.bundleInstance)
            }
        }
    }

    fun onClickNext() {
        if (mViewBinding.position <= 2)
            onClickStep(mViewBinding.position + 1)
    }


}