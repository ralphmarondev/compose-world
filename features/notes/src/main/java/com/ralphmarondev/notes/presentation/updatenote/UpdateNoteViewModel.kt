package com.ralphmarondev.notes.presentation.updatenote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecases.GetNoteByIdUseCase
import com.ralphmarondev.notes.domain.usecases.UpdateNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UpdateNoteViewModel(
    private val noteId: Int,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    private val _note =
        MutableStateFlow(Note(id = -1, title = "", description = "", date = "", time = ""))
    val note: StateFlow<Note> get() = _note

    init {
        getNoteById()
    }

    private fun getNoteById() {
        viewModelScope.launch {
            val fetchedNote = getNoteByIdUseCase(noteId)
            _note.value = fetchedNote
        }
    }

    fun updateNote(note: Note, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            updateNoteUseCase(note, response)
        }
    }
}