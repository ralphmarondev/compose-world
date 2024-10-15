package com.ralphmarondev.composeworld.features.notes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.composeworld.MyApp
import com.ralphmarondev.composeworld.core.model.Note
import com.ralphmarondev.composeworld.features.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.composeworld.features.notes.domain.usecases.GetAllNoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {
    private val noteDao = MyApp.database.noteDao()
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val getAllNoteUseCase = GetAllNoteUseCase(noteRepository)

    val allNotes: StateFlow<List<Note>> = getAllNoteUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}