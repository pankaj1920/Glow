package com.developer.u_glow.viewmodel.authenticate

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.state.authenticate.RegisterState
import com.developer.u_glow.util.Constants

class RegisterViewModel : BaseViewModel<RegisterState>() {

    var bundleInstance: Bundle? = null
    private var isCustomer: Boolean? = null

    private var state: RegisterState = RegisterState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        bundleInstance = bundle
        if (bundle != null){
            if (bundle.containsKey(Constants.BundleKey.isCustomer))
                isCustomer = bundle[Constants.BundleKey.isCustomer] as? Boolean
        }
    }

}