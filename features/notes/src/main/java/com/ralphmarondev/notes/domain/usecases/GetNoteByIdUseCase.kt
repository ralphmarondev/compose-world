package com.ralphmarondev.notes.domain.usecases

import android.util.Log
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository

class GetNoteByIdUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Int): Note {
        return try {
            val note = noteRepository.getNoteById(id)
            note
        } catch (e: Exception) {
            Log.e("NOTE", "Error: ${e.message}")
            Note(id = -1, title = "", description = "", date = "", time = "")
        }
    }
}