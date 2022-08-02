package com.developer.u_glow.state.splash

sealed class SplashState {
    object Init : SplashState()
    object Navigate: SplashState()
}