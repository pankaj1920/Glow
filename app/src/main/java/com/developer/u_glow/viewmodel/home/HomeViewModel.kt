package com.developer.u_glow.viewmodel.home

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.R
import com.developer.u_glow.state.home.HomeState

class HomeViewModel : BaseViewModel<HomeState>() {

    var currentFragment: Int? = null
    var isCustomer: Boolean? = null

    private var state: HomeState = HomeState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        currentFragment = R.id.dashboard
    }

}