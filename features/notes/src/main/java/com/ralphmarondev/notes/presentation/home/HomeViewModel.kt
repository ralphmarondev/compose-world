package com.ralphmarondev.notes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.data.MyApp
import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.usecases.GetAllNoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {
    private val noteDao = MyApp.database.noteDao()
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val getAllNoteUseCase = GetAllNoteUseCase(noteRepository)

    val allNotes: StateFlow<List<com.ralphmarondev.model.Note>> = getAllNoteUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}