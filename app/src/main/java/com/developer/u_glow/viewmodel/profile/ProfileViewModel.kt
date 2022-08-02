package com.developer.u_glow.viewmodel.profile

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.state.profile.ProfileState
import com.developer.u_glow.util.Constants

class ProfileViewModel : BaseViewModel<ProfileState>() {

    var bundleInstance: Bundle? = null

    private var state: ProfileState = ProfileState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        if (bundle != null){
            bundleInstance = bundle
            if (bundle.containsKey(Constants.BundleKey.isShowProgress))
                state = ProfileState.IsShowProgress
        }

    }
}