package com.ralphmarondev.notes.domain.repository

import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun createNote(note: com.ralphmarondev.model.Note)
    suspend fun updateNote(note: com.ralphmarondev.model.Note)
    suspend fun deleteNote(id: Int)

    fun getAllNotes(): Flow<List<com.ralphmarondev.model.Note>>
}