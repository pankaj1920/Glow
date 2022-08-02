package com.developer.u_glow.state.authenticate

import com.developer.u_glow.model.dto.LoginData

sealed class AuthenticateState {
    object Init : AuthenticateState()
    object NavigateToRegister : AuthenticateState()
    data class NavigateHome(val data: LoginData?) : AuthenticateState()
}