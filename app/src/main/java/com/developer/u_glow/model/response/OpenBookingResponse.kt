package com.developer.u_glow.model.response

import com.base.app.model.BaseResponse
import com.developer.u_glow.model.dto.OpenBookingData

data class OpenBookingResponse(
    var data:List<OpenBookingData>
):BaseResponse()
