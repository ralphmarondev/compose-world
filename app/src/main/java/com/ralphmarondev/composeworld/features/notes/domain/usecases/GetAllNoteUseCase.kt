package com.ralphmarondev.composeworld.features.notes.domain.usecases

import com.ralphmarondev.composeworld.core.model.Note
import com.ralphmarondev.composeworld.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNoteUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getAllNotes()
    }
}