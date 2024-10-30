package com.ralphmarondev.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.browser.BrowserMainScreen
import com.ralphmarondev.calculator.CalculatorMainScreen
import com.ralphmarondev.home.HomeScreen

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
                }
            )
        }
//        composable<Routes.Notes> {
//            NoteScreen(
//                backToHome = {
//                    navController.navigateUp()
//                },
//                addNewNote = { navController.navigate(Routes.NewNote) }
//            )
//        }
//        composable<Routes.NewNote> {
//            NewNoteScreen(backToAllNotes = { navController.navigateUp() })
//        }
        composable<Routes.Browser> {
            BrowserMainScreen()
        }
        composable<Routes.Calculator> {
            CalculatorMainScreen()
        }
    }
}