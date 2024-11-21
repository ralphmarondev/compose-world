package com.ralphmarondev.composeworld

import android.app.Application
import androidx.room.Room
import com.ralphmarondev.composeworld.data.local.AppDatabase
import com.ralphmarondev.data.preferences.AppPreferences
import com.ralphmarondev.keepr.data.local.KeeprPreferences

class MyApp : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set

        lateinit var keeprPreferences: KeeprPreferences
            private set

        lateinit var appPreferences: AppPreferences
            private set
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()

        keeprPreferences = KeeprPreferences(applicationContext)
        appPreferences = AppPreferences(applicationContext)
    }
}