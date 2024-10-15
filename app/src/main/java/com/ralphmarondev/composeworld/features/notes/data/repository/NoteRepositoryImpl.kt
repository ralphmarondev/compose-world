package com.ralphmarondev.composeworld.features.notes.data.repository

import com.ralphmarondev.composeworld.core.model.Note
import com.ralphmarondev.composeworld.features.notes.data.local.NoteDao
import com.ralphmarondev.composeworld.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun createNote(note: Note) {
        noteDao.createNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(id: Int) {
        noteDao.deleteNote(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }
}