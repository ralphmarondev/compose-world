package com.ralphmarondev.composeworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.browser.BrowserMainScreen
import com.ralphmarondev.calculator.CalculatorMainScreen
import com.ralphmarondev.composeworld.MyApp
import com.ralphmarondev.home.HomeScreen
import com.ralphmarondev.notes.presentation.home.NoteScreen
import com.ralphmarondev.notes.presentation.newnote.NewNoteScreen
import com.ralphmarondev.settings.presentation.SettingScreen

@Composable
fun AppNavigation() {
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
                database = MyApp.database
            )
        }
        composable<Routes.NewNote> {
            NewNoteScreen(
                backToAllNotes = {
                    navController.navigateUp()
                },
                database = MyApp.database
            )
        }
        composable<Routes.Browser> {
            BrowserMainScreen()
        }
        composable<Routes.Calculator> {
            CalculatorMainScreen()
        }
        composable<Routes.Settings> {
            SettingScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}