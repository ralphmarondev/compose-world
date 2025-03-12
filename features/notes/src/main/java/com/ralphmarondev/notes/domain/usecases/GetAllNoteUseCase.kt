package com.ralphmarondev.notes.domain.usecases

import android.util.Log
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetAllNoteUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
        return flow {
            try {
                noteRepository.getAllNotes().catch { e ->
                    Log.e("App", "Error getting all notes in inner flow: ${e.message}")
                    emit(emptyList())
                }.collect {
                    emit(it)
                }
            } catch (e: Exception) {
                Log.e("App", "Error getting all notes: ${e.message}")
                emit(emptyList())
            }
        }
    }
}