package com.ralphmarondev.notes.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.data.local.AppDatabase
import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecases.UpdateNoteUseCase
import kotlinx.coroutines.launch

class DetailsViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DetailsViewModel(private val database: AppDatabase) : ViewModel() {
    private val noteDao = database.noteDao()
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val updateNoteUseCase = UpdateNoteUseCase(noteRepository)

    // get note by id
    // update note by id
    // delete note by id
    fun updateNote(note: Note, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            updateNoteUseCase.updateNote(note, response)
        }
    }
}