package com.developer.u_glow.model.request

import com.squareup.moshi.Json

data class FbRequest(
    var id: String? = null,
    @Json(name = "access_token")
    var token: String? = null,
    var fcmToken: String? = null,
    var deviceToken: String? = null,
    var platform: String? = null
)