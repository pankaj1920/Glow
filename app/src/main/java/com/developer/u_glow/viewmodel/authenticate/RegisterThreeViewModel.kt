package com.developer.u_glow.viewmodel.authenticate

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.state.authenticate.RegisterOneState
import com.developer.u_glow.state.authenticate.RegisterThreeState
import com.developer.u_glow.util.Constants

class RegisterThreeViewModel : BaseViewModel<RegisterThreeState>() {

    var isCustomer: Boolean? = null

    private var state: RegisterThreeState = RegisterThreeState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        if (bundle != null){
            isCustomer = if (bundle.containsKey(Constants.BundleKey.isCustomer))
                bundle[Constants.BundleKey.isCustomer] as? Boolean
            else
                true
        }
    }

}