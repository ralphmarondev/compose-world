package com.ralphmarondev.composeworld.features.settings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.composeworld.features.settings.presentation.overview.OverviewScreen

@Composable
fun SettingsNavigation(
    navigateBack: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SettingsRoute.Overview
    ) {
        composable<SettingsRoute.Overview> {
            OverviewScreen(
                navController = navController,
                navigateBack = navigateBack
            )
        }
        composable<SettingsRoute.Theme> { }
        composable<SettingsRoute.Terms> { }
        composable<SettingsRoute.License> { }
        composable<SettingsRoute.Developer> { }
    }
}