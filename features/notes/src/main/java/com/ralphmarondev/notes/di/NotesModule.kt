package com.ralphmarondev.notes.di

import com.ralphmarondev.notes.data.local.NoteDatabase
import com.ralphmarondev.notes.data.repository.NoteRepositoryImpl
import com.ralphmarondev.notes.domain.repository.NoteRepository
import com.ralphmarondev.notes.domain.usecases.CreateNoteUseCase
import com.ralphmarondev.notes.domain.usecases.DeleteNoteUseCase
import com.ralphmarondev.notes.domain.usecases.GetAllNoteUseCase
import com.ralphmarondev.notes.domain.usecases.GetNoteByIdUseCase
import com.ralphmarondev.notes.domain.usecases.UpdateNoteUseCase
import com.ralphmarondev.notes.presentation.details.DetailsViewModel
import com.ralphmarondev.notes.presentation.home.HomeViewModel
import com.ralphmarondev.notes.presentation.newnote.NewNoteViewModel
import com.ralphmarondev.notes.presentation.updatenote.UpdateNoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val notesModule = module {
    single { NoteDatabase.createDatabase(androidContext()) }
    single { get<NoteDatabase>().noteDao }
    single<NoteRepository> { NoteRepositoryImpl(get()) }

    factoryOf(::CreateNoteUseCase)
    factoryOf(::DeleteNoteUseCase)
    factoryOf(::GetAllNoteUseCase)
    factoryOf(::GetNoteByIdUseCase)
    factoryOf(::UpdateNoteUseCase)

    viewModelOf(::DetailsViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::NewNoteViewModel)
    viewModelOf(::UpdateNoteViewModel)
}