package com.developer.u_glow.model.response

import com.base.app.model.BaseResponse
import com.developer.u_glow.model.request.Data


data class PostGlowResponse(
    val `data`: Data ? = null
) : BaseResponse()