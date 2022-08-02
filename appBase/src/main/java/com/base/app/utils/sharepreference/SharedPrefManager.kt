package com.base.app.utils.sharepreference

import android.content.Context
import com.android.tadawul.util.sharepreference.SharedPref
import com.base.app.utils.CodeSnippet
import com.base.app.utils.Constants.SharedKey.Companion.FCM_TOKEN
import com.base.app.utils.Constants.SharedKey.Companion.IS_CUSTOMER
import com.base.app.utils.Constants.SharedKey.Companion.ON_BOARDING_VIEWED
import com.base.app.utils.Constants.SharedKey.Companion.TOKEN
import com.base.app.utils.Constants.SharedKey.Companion.USER_DATA


class SharedPrefManager(val context: Context) {

    private var codeSnippet: CodeSnippet = CodeSnippet(context)
    private val sharedPref: SharedPref = SharedPref(context)

    fun clearData() {
        sharedPref.clearSharedValue()
    }

    var token: String
        get() = sharedPref.getStringDefaultValue(TOKEN)
        set(token) = sharedPref.setSharedValue(TOKEN, token)

    var fcmToken: String
        get() = sharedPref.getStringDefaultValue(FCM_TOKEN)
        set(token) = sharedPref.setSharedValue(FCM_TOKEN, token)


    var userData: String?
        get() = sharedPref.getStringDefaultValue(USER_DATA)
        set(token) = sharedPref.setSharedValue(USER_DATA, token)


    var onBoardingViewed : Boolean?
        get() = sharedPref.getBooleanValue(ON_BOARDING_VIEWED)
        set(onBoardingViewed) = sharedPref.setSharedValue(ON_BOARDING_VIEWED,onBoardingViewed)

    var isCustomer : Boolean?
        get() = sharedPref.getBooleanValue(IS_CUSTOMER)
        set(onBoardingViewed) = sharedPref.setSharedValue(IS_CUSTOMER,onBoardingViewed)



}