package com.ralphmarondev.composeworld

import android.app.Application
import androidx.room.Room
import com.ralphmarondev.composeworld.core.data.local.AppDatabase

class MyApp: Application() {
    companion object{
        lateinit var database: AppDatabase
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