package com.ralphmarondev.composeworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.domain.model.KeeprAccount
import com.ralphmarondev.keepr.domain.model.KeeprUser
import com.ralphmarondev.keepr.domain.model.SubCategory
import com.ralphmarondev.keepr.presentation.components.Categories
import com.ralphmarondev.notes.data.local.NoteDao
import com.ralphmarondev.notes.domain.model.Note

@Database(
    entities = [
        Note::class,
        KeeprUser::class,
        Categories::class,
        SubCategory::class,
        KeeprAccount::class
    ],
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