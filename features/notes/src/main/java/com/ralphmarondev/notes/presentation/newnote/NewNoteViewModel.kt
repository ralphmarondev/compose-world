package com.ralphmarondev.notes.presentation.newnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.data.local.AppDatabase
import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecases.CreateNoteUseCase
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class NewNoteViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewNoteViewModel::class.java)) {
            return NewNoteViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class NewNoteViewModel(private val database: AppDatabase) : ViewModel() {
    private val noteDao = database.noteDao()
    private val noteRepository = NoteRepositoryImpl(noteDao)
    private val createNoteUseCase = CreateNoteUseCase(noteRepository)

    fun createNote(note: Note, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            createNoteUseCase.createNote(note, response)
        }
    }

    fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")
        val formattedDate = currentDate.format(formatter)

        return formattedDate
    }

    fun getCurrentTime(): String {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("h:mm a")
        val formattedTime = currentTime.format(formatter)

        return formattedTime
    }
}