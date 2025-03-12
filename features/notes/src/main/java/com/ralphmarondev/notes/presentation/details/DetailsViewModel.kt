package com.ralphmarondev.notes.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.data.local.dao.NoteDao
import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecases.DeleteNoteUseCase
import com.ralphmarondev.notes.domain.usecases.GetNoteByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModelFactory(
    private val noteDao: NoteDao,
    private val noteId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(
                noteDao = noteDao,
                noteId = noteId
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DetailsViewModel(
    private val noteDao: NoteDao,
    private val noteId: Int
) : ViewModel() {
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val getNoteByIdUseCase = GetNoteByIdUseCase(noteRepository)
    private val deleteNoteUseCase = DeleteNoteUseCase(noteRepository)

    private val _note =
        MutableStateFlow(Note(id = -1, title = "", description = "", date = "", time = ""))
    val note: StateFlow<Note> get() = _note

    init {
        getNoteById()
    }

    private fun getNoteById() {
        viewModelScope.launch {
            val fetchedNote = getNoteByIdUseCase.getNoteById(noteId)
            _note.value = fetchedNote
        }
    }

    fun deleteNote(id: Int, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(
                id = id,
                response = response
            )
        }
    }
}