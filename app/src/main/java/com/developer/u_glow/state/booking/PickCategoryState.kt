package com.developer.u_glow.state.booking

import android.os.Bundle
import com.developer.u_glow.adapter.CategoryAdapter
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.model.dto.PeopleData
import com.developer.u_glow.model.dto.PostGlowData

sealed class PickCategoryState {
    object Init : PickCategoryState()
    data class SetPeopleAdapter(val list: ArrayList<PeopleData>) : PickCategoryState()
    data class UpdateCategoryAdapter(var adapter: CategoryAdapter?) : PickCategoryState()
    data class UpdatePickCategoryAdapter(var adapter: CategoryAdapter?) : PickCategoryState()
    data class HideAndShowForMe(
        var forMe: Boolean,
        var forGroup: Boolean,
        var data: CategoryData? = null,
        var id: String? = null,

    ) : PickCategoryState()

    data class NavigatetoSubCategory(var data: CategoryData) : PickCategoryState()
}