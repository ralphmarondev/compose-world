package com.ralphmarondev.user_settings.data.local.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ralphmarondev.user_settings.data.local.database.dao.UserSettingDao
import com.ralphmarondev.user_settings.domain.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserSettingsDatabase : RoomDatabase() {
    abstract val userDao: UserSettingDao

    companion object {
        private const val NAME = "user_database"

        fun createDatabase(context: Context): UserSettingsDatabase {
            try {
                Log.d("App", "Creating database...")
                val database = Room.databaseBuilder(
                    context,
                    UserSettingsDatabase::class.java,
                    NAME
                ).build()
                Log.d("App", "Database created")
                return database
            } catch (e: Exception) {
                Log.e("App", "Error creating database: ${e.message}")
                throw e
            }
        }
    }
}