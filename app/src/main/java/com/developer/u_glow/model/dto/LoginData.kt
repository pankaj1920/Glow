package com.developer.u_glow.model.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class LoginData(
    @Json(name = "lastName")
    var lastName: String? = null,
    @Json(name = "loginType")
    var loginType: Int? = 0,
    @Json(name = "latitude")
    var latitude: String? = "",
    @Json(name = "businessName")
    var businessName: String? = null,
    @Json(name = "emailId")
    var emailId: String? = "",
    @Json(name = "profileImage")
    var profileImage: String? = null,
    @Json(name = "createdAt")
    var createdAt: String? = "",
    @Json(name = "countryCode")
    var countryCode: String? = "",
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "longitude")
    var longitude: String? = "",
    @Json(name = "updatedAt")
    var updatedAt: String? = "",
    @Json(name = "hearAboutUsListId")
    var hearAboutUsListId: Int? = 0,
    @Json(name = "address")
    var address: String? = "",
    @Json(name = "roleId")
    var roleId: Int? = 0,
    @Json(name = "otp")
    var otp: String? = "",
    @Json(name = "token")
    var token: String? = "",
    @Json(name = "phoneNumberVerifiedAt")
    var phoneNumberVerifiedAt: String? = null,
    @Json(name = "firstName")
    var firstName: String? = "",
    @Json(name = "deletedAt")
    var deletedAt: String? = null,
    @Json(name = "statusId")
    var statusId: Int? = 0,
    @Json(name = "phone")
    var phone: String? = "",
    @Json(name = "emailVerifiedAt")
    var emailVerifiedAt: String? = "",
    @Json(name = "socialId")
    var socialId: String? = null,
    @Json(name = "stripeCustomerId")
    var stripeCustomerId: String? = null,
    @Json(name = "isActivate")
    var isActivate: Boolean? = false
) : Parcelable