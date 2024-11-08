package com.ralphmarondev.notes.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.data.local.NoteDao
import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecases.GetNoteByIdUseCase
import com.ralphmarondev.notes.domain.usecases.UpdateNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModelFactory(private val noteDao: NoteDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DetailsViewModel(private val noteDao: NoteDao) : ViewModel() {
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val getNoteByIdUseCase = GetNoteByIdUseCase(noteRepository)
    private val updateNoteUseCase = UpdateNoteUseCase(noteRepository)

    // delete note by id [not implemented yet]
    private val _note =
        MutableStateFlow(Note(id = -1, title = "", description = "", date = "", time = ""))
    val note: StateFlow<Note> get() = _note

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            val fetchedNote = getNoteByIdUseCase.getNoteById(id)
            _note.value = fetchedNote
        }
    }

    fun updateNote(note: Note, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            updateNoteUseCase.updateNote(note, response)
        }
    }
}