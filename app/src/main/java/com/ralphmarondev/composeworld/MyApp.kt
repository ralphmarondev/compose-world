package com.ralphmarondev.composeworld

import android.app.Application
import androidx.room.Room
import com.ralphmarondev.notes.data.local.AppDatabase

class MyApp : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }
}