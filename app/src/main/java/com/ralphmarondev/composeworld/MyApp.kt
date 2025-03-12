package com.ralphmarondev.composeworld

import android.app.Application
import com.ralphmarondev.auth.di.authModule
import com.ralphmarondev.composeworld.home.di.homeModule
import com.ralphmarondev.core.di.coreModule
import com.ralphmarondev.core.worker.scheduleAppWorker
import com.ralphmarondev.notes.di.notesModule
import com.ralphmarondev.onboarding.di.onboardingModule
import com.ralphmarondev.settings.di.settingsModule
import com.ralphmarondev.user_settings.di.userSettingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                coreModule,
                userSettingsModule,
                onboardingModule,
                authModule,
                settingsModule,
                homeModule,
                notesModule
            )
        }

        scheduleAppWorker(applicationContext)
    }
}