package com.ralphmarondev.notes.domain.repository

import com.ralphmarondev.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun createNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(id: Int)
    suspend fun getNoteById(id: Int): Note

    fun getAllNotes(): Flow<List<Note>>
}