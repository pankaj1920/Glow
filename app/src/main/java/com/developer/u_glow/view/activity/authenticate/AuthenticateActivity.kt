package com.developer.u_glow.view.activity.authenticate

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.base.app.utils.encrypt
import com.base.app.utils.getDeviceId
import com.base.app.utils.isEmailValid
import com.base.app.utils.moshiObjToString
import com.base.app.view.BaseActivity
import com.developer.u_glow.R
import com.developer.u_glow.databinding.ActivityAuthenticationBinding
import com.developer.u_glow.model.dto.LoginData
import com.developer.u_glow.model.request.FbRequest
import com.developer.u_glow.model.request.GoogleRequest
import com.developer.u_glow.model.request.LoginRequest
import com.developer.u_glow.state.authenticate.AuthenticateState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.util.NotificationHelper
import com.developer.u_glow.util.facebook.FacebookLogin
import com.developer.u_glow.util.facebook.IFbListener
import com.developer.u_glow.util.google.GoogleSignIn
import com.developer.u_glow.viewmodel.authenticate.AuthenticateViewModel
import com.facebook.CallbackManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.alert_forgot_password.view.*
import timber.log.Timber

@Suppress("DEPRECATION")
class AuthenticateActivity(override val layoutId: Int = R.layout.activity_authentication) :
    BaseActivity<AuthenticateViewModel, ActivityAuthenticationBinding>() {

    override val mViewModel: AuthenticateViewModel by viewModels()
    private var facebookLogin: CallbackManager? = null

    override fun onInitialize() {
        mViewBinding.isLogin = true
        mViewBinding.view = this
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is AuthenticateState.NavigateHome -> {
                    sharedPreferences.userData = it.data?.moshiObjToString(LoginData::class.java)!!
                    it.data.token?.let { token ->
                        sharedPreferences.token = token
                    }
                    sharedPreferences.isCustomer = it.data.roleId == Constants.RoleType.customer
                    navigateNew(null, Constants.Activity.home)
                }

                is AuthenticateState.NavigateToRegister -> {
                    mViewBinding.isLogin = false
                }
            }
        })
    }

    fun onClickLogin(isLogin: Boolean) {
        mViewBinding.isLogin = isLogin
    }

    fun onClickRegister(isCustomer: Boolean) {
        navigateTo(mViewModel.getBundle(isCustomer), Constants.Activity.register)
    }

    fun onClickForgotPassword() {
        val alert = createAlert(R.layout.alert_forgot_password)
        alert.first.btnDone.setOnClickListener { alert.second.dismiss() }
        alert.second.show()
    }

    fun performLogin() {

        when {
            TextUtils.isEmpty(mViewBinding.edtMobileEmail.text.toString().trim()) -> showMessage(
                resources.getString(R.string.alert_enter_email)
            )
            TextUtils.isEmpty(mViewBinding.edtPassword.text.toString().trim()) -> showMessage(
                resources.getString(R.string.alert_enter_password)
            )
            mViewBinding.edtMobileEmail.text.toString().trim().isEmailValid().not() -> showMessage(
                resources.getString(R.string.alert_enter_valid_email)
            )
            else -> {
                if (sharedPreferences.token.isEmpty())
                    NotificationHelper(applicationContext).getRefreshToken()

                val loginRequest = LoginRequest()
                loginRequest.emailId = mViewBinding.edtMobileEmail.text.toString().trim()
                loginRequest.password = mViewBinding.edtPassword.text.toString().trim().encrypt()
                loginRequest.fcmToken = sharedPreferences.fcmToken
                loginRequest.deviceToken = getDeviceId()
                loginRequest.platform = Constants.Platform.android

                mViewModel.performLogin(
                    loginRequest
                )
            }
        }

    }

    fun onClickGoogle() {
        GoogleSignIn().init(this, resultLauncher)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result != null) {
                val task: Task<GoogleSignInAccount> =
                    com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(
                        result.data
                    )
                try {
                    val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                    if (account.idToken.isNullOrEmpty().not()) {
                        val request = GoogleRequest()
                        request.idToken = account.idToken
                        request.fcmToken = sharedPreferences.fcmToken
                        request.deviceToken = getDeviceId()
                        request.platform = Constants.Platform.android
                        mViewModel.performGoogleLogin(request)
                    }

                    Timber.d("===TokenID===${account.idToken}")
                } catch (e: ApiException) {
                    showMessage(e.localizedMessage ?: e.message ?: "Something went wrong")
                }
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookLogin?.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)

    }

    fun onClickFacebook() {
        facebookLogin = FacebookLogin().init(this, iFbListener)
    }

    private val iFbListener = object : IFbListener {
        override fun onSuccess(loginResult: LoginResult?) {
            if (loginResult?.accessToken != null) {
                val request = FbRequest()
                request.id = loginResult.accessToken?.userId
                request.token = loginResult.accessToken?.token
                request.fcmToken = sharedPreferences.fcmToken
                request.deviceToken = getDeviceId()
                request.platform = Constants.Platform.android
                mViewModel.performFbLogin(request)
            }
        }

        override fun onFailure(message: String) {
            showMessage(message)
        }

    }
}