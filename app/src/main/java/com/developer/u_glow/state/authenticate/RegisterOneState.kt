package com.developer.u_glow.state.authenticate

sealed class RegisterOneState {
    object Init : RegisterOneState()
    data class UpdateUserType(val isCustomer: Boolean) : RegisterOneState()
}