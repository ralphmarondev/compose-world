package com.ralphmarondev.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ralphmarondev.notes.data.local.dao.NoteDao
import com.ralphmarondev.notes.presentation.details.DetailScreen
import com.ralphmarondev.notes.presentation.home.HomeScreen
import com.ralphmarondev.notes.presentation.newnote.NewNoteScreen
import com.ralphmarondev.notes.presentation.updatenote.UpdateNoteScreen

@Composable
fun NotesNavigation(
    navigateBack: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                backToHome = navigateBack,
                addNewNote = {
                    navController.navigate(Routes.NewNote)
                },
                navigateToDetails = { id ->
                    navController.navigate(Routes.NoteDetails(id))
                }
            )
        }
        composable<Routes.NewNote> {
            NewNoteScreen(
                backToAllNotes = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.NoteDetails> {
            val args = it.toRoute<Routes.NoteDetails>()
            DetailScreen(
                backToAllNotes = {
                    navController.navigateUp()
                },
                navigateToUpdateNote = {
                    navController.navigate(Routes.UpdateNote(args.id))
                },
                noteId = args.id
            )
        }
        composable<Routes.UpdateNote> {
            val args = it.toRoute<Routes.UpdateNote>()
            UpdateNoteScreen(
                backToNoteDetails = {
                    navController.navigateUp()
                },
                noteId = args.id
            )
        }
    }
}