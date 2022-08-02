package com.developer.u_glow.viewmodel.onboarding

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.state.onboarding.OnBoardFragmentState
import com.developer.u_glow.state.onboarding.OnBoardState
import com.developer.u_glow.util.Constants

class OnBoardFragmentViewModel : BaseViewModel<OnBoardFragmentState>() {

    private var onBoardState: OnBoardFragmentState = OnBoardFragmentState.Init
        set(value) {
            field = value
            publishState(onBoardState)
        }

    override fun onInitialized(bundle: Bundle?) {
        if (bundle != null && bundle.containsKey(Constants.BundleKey.position)){
            onBoardState = OnBoardFragmentState.UpdateOnBoardData(bundle[Constants.BundleKey.position] as Int)
        }
    }
}