package com.developer.u_glow.model.request

import com.squareup.moshi.Json

data class GoogleRequest(
    @Json(name = "id_token")
    var idToken: String? = null,
    var fcmToken: String? = null,
    var deviceToken: String? = null,
    var platform: String? = null,
)