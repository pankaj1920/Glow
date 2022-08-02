package com.developer.u_glow.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class PostGlowData(
    var position: Int? = null,
    var forMe: Boolean? = null,
    var forGroup: Boolean? = null,
    var id: String?=null,
    var subId: List<Int>?=null,
    var subName: String? = null,
    var serviceName: String? = null,
    var servicesList: @RawValue List<Services>?=null,
    var subCategoryPosition: String? = null,
    var userId: Int?=null,
    var amount: String?=null,
    var numberOfPeople: Int?=null,
    var date: String?=null,
    var time: String?=null,
    var isGroup: Boolean?=null,
    var alternativeDate: String?=null,
    var alternativeTime: String?=null,
    var otherService: String?=null,
    var detail: String?=null,
    var address: String?=null,
    var latitude: Double?=null,
    var longitude: Double?=null,
    var isFlexible:Boolean?=false,
    var services: List<Int>?=null,
    var imageUrl:List<String>?=null,
) : Parcelable


