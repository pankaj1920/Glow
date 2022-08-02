package com.developer.u_glow.model.dto

data class CategoryData(
    val id: String? = "",
    val name: String? = "",
    val image: String? = "",
    val forMe: Boolean? = false,
    val forGroup: Boolean? = false,
    val greenImage: String? = "",
    val hasSubCategories: Boolean? = false,
    var superServiceId: String? = "",
    var select: Boolean=false,

)