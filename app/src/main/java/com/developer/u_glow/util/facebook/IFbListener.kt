package com.developer.u_glow.util.facebook

import com.facebook.login.LoginResult

interface IFbListener {
    fun onSuccess(loginResult: LoginResult?)
    fun onFailure(message: String)
}