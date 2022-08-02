package com.developer.u_glow.model.response

import com.base.app.model.BaseResponse
import com.developer.u_glow.model.dto.LoginData

data class LoginResponse(
    val data: LoginData? = null
) : BaseResponse()