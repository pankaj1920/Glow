package com.developer.u_glow.viewmodel.authenticate

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.state.authenticate.RegisterTwoState

class RegisterTwoViewModel : BaseViewModel<RegisterTwoState>() {


    private var state: RegisterTwoState = RegisterTwoState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {

    }

}