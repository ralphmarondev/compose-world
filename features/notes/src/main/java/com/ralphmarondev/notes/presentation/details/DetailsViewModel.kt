package com.ralphmarondev.notes.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecases.DeleteNoteUseCase
import com.ralphmarondev.notes.domain.usecases.GetNoteByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val noteId: Int,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _note =
        MutableStateFlow(Note(id = -1, title = "", description = "", date = "", time = ""))
    val note = _note.asStateFlow()

    init {
        getNoteById()
    }

    private fun getNoteById() {
        viewModelScope.launch {
            val fetchedNote = getNoteByIdUseCase(noteId)
            _note.value = fetchedNote
        }
    }

    fun deleteNote(id: Int, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            deleteNoteUseCase(
                id = id,
                response = response
            )
        }
    }
}