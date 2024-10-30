package com.ralphmarondev.notes.domain.usecases

import com.ralphmarondev.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNoteUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(): Flow<List<com.ralphmarondev.model.Note>> {
        return noteRepository.getAllNotes()
    }
}