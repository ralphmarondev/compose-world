package com.ralphmarondev.composeworld.features.notes.domain.usecases

import android.util.Log
import com.ralphmarondev.composeworld.core.model.Note
import com.ralphmarondev.composeworld.features.notes.domain.repository.NoteRepository

class CreateNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun createNote(note: Note, response: (Boolean, String?) -> Unit) {
        try {
            noteRepository.createNote(note)
            response(true, "Success.")
        } catch (e: Exception) {
            Log.e("NOTE", "Error: ${e.message}")
            response(false, e.message)
        }
    }
}