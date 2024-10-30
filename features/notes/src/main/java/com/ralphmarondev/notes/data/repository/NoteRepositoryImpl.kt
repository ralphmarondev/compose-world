package com.ralphmarondev.notes.data.repository

import com.ralphmarondev.notes.data.local.NoteDao
import com.ralphmarondev.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun createNote(note: com.ralphmarondev.model.Note) {
        noteDao.createNote(note)
    }

    override suspend fun updateNote(note: com.ralphmarondev.model.Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(id: Int) {
        noteDao.deleteNote(id)
    }

    override fun getAllNotes(): Flow<List<com.ralphmarondev.model.Note>> {
        return noteDao.getAllNotes()
    }
}