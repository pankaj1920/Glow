package com.developer.u_glow.viewmodel.authenticate

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.base.app.model.State
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.model.request.FbRequest
import com.developer.u_glow.model.request.GoogleRequest
import com.developer.u_glow.model.request.LoginRequest
import com.developer.u_glow.model.response.LoginResponse
import com.developer.u_glow.state.authenticate.AuthenticateState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.webservices.ModelRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthenticateViewModel : BaseViewModel<AuthenticateState>() {


    private var state: AuthenticateState = AuthenticateState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {

    }

    fun getBundle(customer: Boolean): Bundle? {
        val bundle = Bundle()
        bundle.putBoolean(Constants.BundleKey.isCustomer, customer)
        return bundle
    }

    fun performLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            ModelRepository(iRepositoryListener)
                .performLogin(loginRequest).collect {
                    when (it) {
                        is State.Success -> {
                            handleSuccess(it.data)
                        }
                    }
                }
        }
    }

    fun performGoogleLogin(request: GoogleRequest) {
        viewModelScope.launch {
            ModelRepository(iRepositoryListener)
                .performGoogleLogin(request).collect {
                    when (it) {
                        is State.Success -> {
                            handleSuccess(response = it.data)
                        }
                    }
                }
        }
    }

    private fun handleSuccess(response: LoginResponse) {
        response.message?.let { message -> showMessage(message) }
        if (response.data != null)
            state = AuthenticateState.NavigateHome(response.data)
    }

    fun performFbLogin(request: FbRequest) {
        viewModelScope.launch {
            ModelRepository(iRepositoryListener)
                .performFbLogin(request).collect {
                    when (it) {
                        is State.Success -> {
                            if (it.data.status == com.base.app.utils.Constants.InternalHttpCode.SUCCESS)
                                handleSuccess(response = it.data)
                            else
                                state = AuthenticateState.NavigateToRegister
                        }
                    }
                }
        }
    }

}