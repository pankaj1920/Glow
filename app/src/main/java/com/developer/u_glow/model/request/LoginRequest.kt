package com.developer.u_glow.model.request

data class LoginRequest(
    var emailId: String? = null,
    var password: String? = null,
    var fcmToken: String? = null,
    var deviceToken: String? = null,
    var platform: String? = null
)