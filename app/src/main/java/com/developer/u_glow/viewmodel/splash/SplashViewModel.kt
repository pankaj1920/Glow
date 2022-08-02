package com.developer.u_glow.viewmodel.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.state.splash.SplashState
import com.developer.u_glow.util.Constants

class SplashViewModel : BaseViewModel<SplashState>(){

    private var splashState: SplashState = SplashState.Init
        set(value) {
            field = value
            publishState(splashState)
        }

    override fun onInitialized(bundle: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            splashState = SplashState.Navigate
        }, Constants.HandlerTime.splashTime)
    }
}