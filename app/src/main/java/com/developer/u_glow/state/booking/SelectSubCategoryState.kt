package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.CategoryAdapter
import com.developer.u_glow.adapter.SubCategoryAdapter
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.model.dto.PeopleData
import com.developer.u_glow.model.dto.PositionData
import com.developer.u_glow.model.dto.PostGlowData

sealed class SelectSubCategoryState {
    object Init : SelectSubCategoryState()
    data class UpdateCategoryAdapter(var adapter: CategoryAdapter?) : SelectSubCategoryState()
    data class UpdateSubCategoryAdapter(var adapter: SubCategoryAdapter?) : SelectSubCategoryState()
    data class ReSendDataToService(
        var list: ArrayList<PostGlowData>? = null,
        var postGlow: PostGlowData? = null
    ) :
        SelectSubCategoryState()

    data class DeleteService(var position: Int? = null) : SelectSubCategoryState()
    data class NavigateToService(
        var position: Int? = null,
        var id: String,
        var data: CategoryData? = null, var subId: List<Int>? = null
    ) : SelectSubCategoryState()

    data class NavigateToServiceFromAlert(
        var id: String? = null,
        var selectedPositionList: MutableList<PositionData>? = ArrayList()
    ) : SelectSubCategoryState()
}