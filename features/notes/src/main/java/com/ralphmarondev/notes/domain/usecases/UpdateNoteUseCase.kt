package com.ralphmarondev.notes.domain.usecases

import android.util.Log
import com.ralphmarondev.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository

class UpdateNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun updateNote(note: com.ralphmarondev.model.Note, response: (Boolean, String?) -> Unit) {
        try {
            noteRepository.updateNote(note)
            response(true, "Success.")
        } catch (e: Exception) {
            Log.e("NOTE", "Error: ${e.message}")
            response(false, e.message)
        }
    }
}