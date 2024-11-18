package com.ralphmarondev.settings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.settings.presentation.appearance.fonts.AppFontStyleScreen
import com.ralphmarondev.settings.presentation.appearance.theme.AppThemeScreen
import com.ralphmarondev.settings.presentation.home.HomeScreen

@Composable
fun SettingsNavigation(
    darkTheme: Boolean,
    toggleDarkTheme: () -> Unit,
    navigateBack: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                navigateBack = navigateBack,
                navigateToAppTheme = {
                    navController.navigate(Routes.Appearance.AppTheme)
                },
                navigateToAppFontStyle = {
                    navController.navigate(Routes.Appearance.FontStyleAndSize)
                }
            )
        }
        composable<Routes.Appearance.AppTheme> {
            AppThemeScreen(
                navigateBack = { navController.navigateUp() },
                darkTheme = darkTheme,
                toggleDarkTheme = toggleDarkTheme
            )
        }
        composable<Routes.Appearance.FontStyleAndSize> {
            AppFontStyleScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}