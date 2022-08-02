package com.developer.u_glow.util.facebook

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.util.*


class FacebookLogin {

    fun init(context: AppCompatActivity, listener: IFbListener): CallbackManager{
        val callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(context, listOf("email"));
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    listener.onSuccess(loginResult)
                }

                override fun onCancel() {
                }

                override fun onError(exception: FacebookException) {
                    listener.onFailure(exception.localizedMessage ?: exception.message ?: "Something went wrong.")
                }
            })

        return callbackManager
    }
}