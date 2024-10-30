package com.ralphmarondev.notes.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<com.ralphmarondev.model.Note>>

    @Upsert
    suspend fun createNote(note: com.ralphmarondev.model.Note)

    @Update
    suspend fun updateNote(note: com.ralphmarondev.model.Note)

    @Query("DELETE FROM Note WHERE id=:id")
    suspend fun deleteNote(id: Int)
}