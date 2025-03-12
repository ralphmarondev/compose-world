package com.ralphmarondev.notes.domain.usecases

import android.util.Log
import com.ralphmarondev.notes.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun deleteNote(id: Int, response: (Boolean, String?) -> Unit) {
        try {
            noteRepository.deleteNote(id)
            response(true, "Success.")
        } catch (e: Exception) {
            Log.e("NOTE", "Error: ${e.message}")
            response(false, e.message)
        }
    }
}