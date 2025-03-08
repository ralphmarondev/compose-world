package com.ralphmarondev.core.di

import androidx.work.WorkManager
import com.ralphmarondev.core.data.local.preferences.AppPreferences
import com.ralphmarondev.core.notification.AppNotification
import com.ralphmarondev.core.util.ThemeState
import org.koin.dsl.module

val coreModule = module {
    // preferences
    single { AppPreferences(get()) }
    single { ThemeState(get()) }

    // worker
    single { WorkManager.getInstance(get()) }

    // notification
    factory { AppNotification(get()) }
}