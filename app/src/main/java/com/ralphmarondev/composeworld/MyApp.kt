package com.ralphmarondev.composeworld

import android.app.Application
import com.ralphmarondev.composeworld.di.appModule
import com.ralphmarondev.core.worker.scheduleAppWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }

        scheduleAppWorker(applicationContext)
    }
}