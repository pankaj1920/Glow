package com.developer.u_glow.model.response

import com.base.app.model.BaseResponse
import com.developer.u_glow.model.dto.CategoryData

data class CategoryResponse(
    val data:MutableList<CategoryData>? = null
): BaseResponse()
