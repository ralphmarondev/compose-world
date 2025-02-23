package com.ralphmarondev.composeworld

import android.app.Application
import com.ralphmarondev.composeworld.core.di.coreModule
import com.ralphmarondev.composeworld.features.auth.di.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                coreModule,
                authModule
            )
        }
    }
}