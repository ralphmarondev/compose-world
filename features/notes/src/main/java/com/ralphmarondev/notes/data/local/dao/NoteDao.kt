package com.ralphmarondev.notes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ralphmarondev.notes.domain.model.Note
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

    @Query("SELECT * FROM Note where id=:id")
    suspend fun getNoteById(id: Int): Note
}