package com.ralphmarondev.composeworld.features.notes.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ralphmarondev.composeworld.core.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>

    @Upsert
    suspend fun createNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM Note WHERE id=:id")
    suspend fun deleteNote(id: Int)
}