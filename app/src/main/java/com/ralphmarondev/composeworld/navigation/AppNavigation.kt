package com.ralphmarondev.composeworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ralphmarondev.calculator.CalculatorMainScreen
import com.ralphmarondev.composeworld.MyApp
import com.ralphmarondev.home.HomeScreen
import com.ralphmarondev.keepr.navigation.KeeprNavigation
import com.ralphmarondev.notes.presentation.details.DetailScreen
import com.ralphmarondev.notes.presentation.home.NoteScreen
import com.ralphmarondev.notes.presentation.newnote.NewNoteScreen
import com.ralphmarondev.notes.presentation.updatenote.UpdateNoteScreen
import com.ralphmarondev.settings.navigation.SettingsNavigation

@Composable
fun AppNavigation(
    darkTheme: Boolean,
    toggleDarkTheme: () -> Unit
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                navigateToNotes = {
                    navController.navigate(Routes.Notes)
                },
                navigateToBrowser = {
                    navController.navigate(Routes.Browser)
                },
                navigateToCalculator = {
                    navController.navigate(Routes.Calculator)
                },
                navigateToSettings = {
                    navController.navigate(Routes.Settings)
                },
                navigateToKeeper = {
                    navController.navigate(Routes.Keeper)
                }
            )
        }
        composable<Routes.Notes> {
            NoteScreen(
                backToHome = {
                    navController.navigateUp()
                },
                addNewNote = {
                    navController.navigate(Routes.NewNote)
                },
                noteDao = MyApp.database.noteDao(),
                navigateToDetails = { noteId ->
                    navController.navigate(Routes.NoteDetails(noteId))
                }
            )
        }
        composable<Routes.NewNote> {
            NewNoteScreen(
                backToAllNotes = {
                    navController.navigateUp()
                },
                noteDao = MyApp.database.noteDao()
            )
        }
        composable<Routes.NoteDetails> {
            val args = it.toRoute<Routes.NoteDetails>()
            DetailScreen(
                backToAllNotes = {
                    navController.navigateUp()
                },
                noteDao = MyApp.database.noteDao(),
                noteId = args.id,
                navigateToUpdateNote = {
                    navController.navigate(Routes.UpdateNote(args.id))
                }
            )
        }
        composable<Routes.UpdateNote> {
            val args = it.toRoute<Routes.UpdateNote>()
            UpdateNoteScreen(
                backToNoteDetails = {
                    navController.navigateUp()
                },
                noteDao = MyApp.database.noteDao(),
                noteId = args.id
            )
        }
        composable<Routes.Browser> {
            com.ralphmarondev.browser.presentation.home.HomeScreen()
        }
        composable<Routes.Calculator> {
            CalculatorMainScreen()
        }
        composable<Routes.Settings> {
            SettingsNavigation(
                navigateBack = {
                    navController.navigateUp()
                },
                darkTheme = darkTheme,
                toggleDarkTheme = toggleDarkTheme
            )
        }
        composable<Routes.Keeper> {
            KeeprNavigation(
                navigateBack = {
                    navController.navigateUp()
                },
                keeprDao = MyApp.database.keeprDao(),
                preferences = MyApp.keeprPreferences
            )
        }
    }
}