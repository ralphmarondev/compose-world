package com.ralphmarondev.notes.presentation.newnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecases.CreateNoteUseCase
import kotlinx.coroutines.launch

class NewNoteViewModel(
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel() {

    fun createNote(note: Note, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            createNoteUseCase(note, response)
        }
    }
}