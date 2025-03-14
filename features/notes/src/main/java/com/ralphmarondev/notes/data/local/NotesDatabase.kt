package com.ralphmarondev.notes.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ralphmarondev.notes.data.local.dao.NoteDao
import com.ralphmarondev.notes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        private const val NAME = "note_database"

        fun createDatabase(context: Context): NoteDatabase {
            try {
                Log.d("App", "Creating database...")
                val database = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    NAME
                ).build()
                Log.d("App", "Database created successfully.")
                return database
            } catch (e: Exception) {
                Log.e("App", "Error creating database: ${e.message}")
                throw e
            }
        }
    }
}