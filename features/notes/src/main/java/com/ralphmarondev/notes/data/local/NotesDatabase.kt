package com.ralphmarondev.notes.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ralphmarondev.notes.data.local.dao.NoteDao

abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        private const val NAME = "note_database"

        fun createDatabase(context: Context): NoteDatabase {
            try {
                val database = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    NAME
                ).build()
                return database
            } catch (e: Exception) {
                throw e
            }
        }
    }
}