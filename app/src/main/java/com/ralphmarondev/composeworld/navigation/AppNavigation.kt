package com.ralphmarondev.composeworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.browser.navigation.BrowserNavigation
import com.ralphmarondev.calculator.navigation.CalculatorNavigation
import com.ralphmarondev.clock.navigation.ClockNavigation
import com.ralphmarondev.composeworld.MyApp
import com.ralphmarondev.data.preferences.AppPreferences
import com.ralphmarondev.home.HomeScreen
import com.ralphmarondev.keepr.navigation.KeeprNavigation
import com.ralphmarondev.notes.navigation.NotesNavigation
import com.ralphmarondev.onboarding.navigation.OnBoardingNavigation
import com.ralphmarondev.settings.navigation.SettingsNavigation

@Composable
fun AppNavigation(
    darkTheme: Boolean,
    preferences: AppPreferences,
    toggleDarkTheme: () -> Unit
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (preferences.isFirstLaunch()) Routes.Onboarding else Routes.Home
    ) {
        composable<Routes.Onboarding> {
            OnBoardingNavigation(
                navigateToHome = {
                    navController.popBackStack() // we will not go back here again after success registration
                    navController.navigate(Routes.Home)
                },
                dao = MyApp.database.userDao(),
                preferences = preferences
            )
        }
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
                },
                navigateToClock = {
                    navController.navigate(Routes.Clock)
                }
            )
        }
        composable<Routes.Notes> {
            NotesNavigation(
                navigateBack = {
                    navController.navigateUp()
                },
                noteDao = MyApp.database.noteDao()
            )
        }
        composable<Routes.Browser> {
            BrowserNavigation(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Calculator> {
            CalculatorNavigation(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Settings> {
            SettingsNavigation(
                navigateBack = {
                    navController.navigateUp()
                },
                darkTheme = darkTheme,
                toggleDarkTheme = toggleDarkTheme,
                dao = MyApp.database.userDao(),
                currentUser = preferences.getCurrentUser() // NOTE: Get this from login later
            )
        }
        composable<Routes.Keeper> {
            KeeprNavigation(
                navigateBack = {
                    navController.navigateUp()
                },
                keeprDao = MyApp.database.keeprDao(),
                preferences = MyApp.keeprPreferences,
                currentUser = preferences.getCurrentUser()
            )
        }
        composable<Routes.Clock> {
            ClockNavigation(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}