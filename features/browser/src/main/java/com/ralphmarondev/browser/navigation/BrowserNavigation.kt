package com.ralphmarondev.browser.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.browser.presentation.home.HomeScreen

@Composable
fun BrowserNavigation(
    navigateBack: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen()
        }
    }
}