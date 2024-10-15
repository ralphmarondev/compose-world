package com.ralphmarondev.composeworld.features.notes.presentation.newnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.composeworld.MyApp
import com.ralphmarondev.composeworld.core.model.Note
import com.ralphmarondev.composeworld.features.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.composeworld.features.notes.domain.usecases.CreateNoteUseCase
import kotlinx.coroutines.launch

class NewNoteViewModel : ViewModel() {
    private val noteDao = MyApp.database.noteDao()
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val createNoteUseCase = CreateNoteUseCase(noteRepository)

    fun createNote(note: Note, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            createNoteUseCase.createNote(note, response)
        }
    }
}