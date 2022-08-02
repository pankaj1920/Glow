package com.developer.u_glow.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnBoardSliderData(
    var title : Int,
    var desc : Int,
    var img : Int
) : Parcelable