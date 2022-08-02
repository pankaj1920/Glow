package com.developer.u_glow.util.google

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class GoogleSignIn {


    fun init(
        context: AppCompatActivity,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("272622142768-kojvll0vhceo7uuiei63dgtohff5gvdn.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        resultLauncher.launch(googleSignInClient.signInIntent)

    }
}