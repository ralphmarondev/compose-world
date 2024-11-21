package com.ralphmarondev.composeworld.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ralphmarondev.data.user.User
import com.ralphmarondev.data.user.UserDao
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.domain.model.Account
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.Subcategory
import com.ralphmarondev.notes.data.local.NoteDao
import com.ralphmarondev.notes.domain.model.Note

@Database(
    entities = [
        Note::class,
        User::class,
        Category::class,
        Subcategory::class,
        Account::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "compose_world"
    }

    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao
    abstract fun keeprDao(): KeeprDao
}