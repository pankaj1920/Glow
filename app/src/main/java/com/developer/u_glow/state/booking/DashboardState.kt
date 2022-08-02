package com.developer.u_glow.state.booking

import android.os.Bundle
import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.adapter.CategoryAdapter
import com.developer.u_glow.model.dto.CategoryData

sealed class DashboardState {
    object Init : DashboardState()
    data class UpdateCategoryAdapter(var adapter: CategoryAdapter?) : DashboardState()
    data class UpdateBookingAdapter(var adapter: BookingAdapter?) : DashboardState()
    data class NavigateToSubCategory(var data: CategoryData,var position:Int,var bundle: Bundle?=null) : DashboardState()
    data class ShowMore(var showMore: Boolean) : DashboardState()
}