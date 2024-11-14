package com.ralphmarondev.composeworld

import android.app.Application
import androidx.room.Room
import com.ralphmarondev.composeworld.data.local.AppDatabase
import com.ralphmarondev.keepr.data.local.PreferencesHelper

class MyApp : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set

        lateinit var keeprPreferences: PreferencesHelper
            private set
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()

        keeprPreferences = PreferencesHelper(applicationContext)
    }
}