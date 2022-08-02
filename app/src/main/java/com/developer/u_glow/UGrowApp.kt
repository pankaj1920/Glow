package com.developer.u_glow

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.developer.u_glow.di.helperModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@ExperimentalCoroutinesApi
class UGrowApp : Application()  {
    var context: Context? = null
    override fun onCreate() {
        super.onCreate()


        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@UGrowApp)
            modules(
                listOf(
                    helperModule
                )
            )
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }



    fun getAppContext(): Context? {
        return context
    }




}
