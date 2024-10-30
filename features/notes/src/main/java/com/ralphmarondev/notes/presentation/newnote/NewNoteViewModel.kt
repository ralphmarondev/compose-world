package com.ralphmarondev.notes.presentation.newnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.data.MyApp
import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.usecases.CreateNoteUseCase
import kotlinx.coroutines.launch

class NewNoteViewModel : ViewModel() {
    private val noteDao = com.ralphmarondev.data.MyApp.database.noteDao()
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val createNoteUseCase = CreateNoteUseCase(noteRepository)

    fun createNote(note: com.ralphmarondev.model.Note, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            createNoteUseCase.createNote(note, response)
        }
    }
}