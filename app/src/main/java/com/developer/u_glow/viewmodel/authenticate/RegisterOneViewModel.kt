package com.developer.u_glow.viewmodel.authenticate

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.state.authenticate.RegisterOneState
import com.developer.u_glow.state.authenticate.RegisterThreeState
import com.developer.u_glow.util.Constants

class RegisterOneViewModel : BaseViewModel<RegisterOneState>() {


    private var state: RegisterOneState = RegisterOneState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        if (bundle != null){
            if (bundle.containsKey(Constants.BundleKey.isCustomer))
                state = RegisterOneState.UpdateUserType(bundle[Constants.BundleKey.isCustomer] as Boolean)
        }
    }

}