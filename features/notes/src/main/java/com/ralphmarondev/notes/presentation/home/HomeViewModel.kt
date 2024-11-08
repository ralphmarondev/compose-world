package com.ralphmarondev.notes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.data.local.NoteDao
import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecases.GetAllNoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModelFactory(private val noteDao: NoteDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}

class HomeViewModel(private val noteDao: NoteDao) : ViewModel() {
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val getAllNoteUseCase = GetAllNoteUseCase(noteRepository)

    val allNotes: StateFlow<List<Note>> = getAllNoteUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}