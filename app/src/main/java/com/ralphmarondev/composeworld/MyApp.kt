package com.ralphmarondev.composeworld

import android.app.Application
import com.google.firebase.FirebaseApp
import com.ralphmarondev.composeworld.di.appModule
import com.ralphmarondev.core.worker.scheduleAppWorker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this@MyApp)

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }

        scheduleAppWorker(applicationContext)
    }
}