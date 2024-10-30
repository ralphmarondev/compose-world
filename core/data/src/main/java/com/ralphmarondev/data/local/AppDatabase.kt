package com.ralphmarondev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ralphmarondev.model.Note
import com.ralphmarondev.notes.data.local.NoteDao

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "compose_world"
    }

    abstract fun noteDao(): NoteDao
}