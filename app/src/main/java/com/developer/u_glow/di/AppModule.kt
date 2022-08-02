package com.developer.u_glow.di

import com.base.app.utils.CodeSnippet
import com.base.app.utils.sharepreference.SharedPrefManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


/**
 * Contains all the required dependencies
 * */

val helperModule = module {
    single { CodeSnippet(androidContext()) }
    single { SharedPrefManager(androidContext()) }
}


