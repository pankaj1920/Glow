package com.developer.u_glow.model.response

import com.base.app.model.BaseResponse
import com.developer.u_glow.model.dto.ServiceData

class SelectServiceRespose(
    val `data`: List<ServiceData>?=null,
): BaseResponse()