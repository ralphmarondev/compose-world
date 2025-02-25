package com.ralphmarondev.composeworld.core.data.local.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ralphmarondev.composeworld.core.data.local.user.UserDao
import com.ralphmarondev.composeworld.core.data.local.user.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "app_database"

        fun createDatabase(context: Context): AppDatabase {
            try {
                Log.d("DB", "Creating database...")
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()

                Log.d("DB", "Database created successfully")
                return database
            } catch (e: Exception) {
                Log.e("DB", "Error creating database", e)
                throw e
            }
        }
    }
}