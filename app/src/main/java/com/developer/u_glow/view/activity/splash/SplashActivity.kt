package com.developer.u_glow.view.activity.splash

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.base.app.view.BaseActivity
import com.developer.u_glow.R
import com.developer.u_glow.databinding.ActivitySplashBinding
import com.developer.u_glow.state.splash.SplashState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.viewmodel.splash.SplashViewModel

class SplashActivity(override val layoutId: Int = R.layout.activity_splash) :
    BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val mViewModel: SplashViewModel by viewModels()

    override fun onInitialize() {
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is SplashState.Navigate -> {
                    if (sharedPreferences.token.isEmpty())
                        navigateNew(null, Constants.Activity.onBoarding)
                    else
                        navigateNew(null, Constants.Activity.home)
                }

                else -> {
                }
            }
        })
    }
}