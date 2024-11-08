package com.ralphmarondev.composeworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.domain.model.KeeprUser
import com.ralphmarondev.notes.data.local.NoteDao
import com.ralphmarondev.notes.domain.model.Note

@Database(
    entities = [Note::class, KeeprUser::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "compose_world"
    }

    abstract fun noteDao(): NoteDao
    abstract fun keeprDao(): KeeprDao
}