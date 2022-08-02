package com.developer.u_glow.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PositionData(
    var subCategoryPosition: Int? = null
):Parcelable
