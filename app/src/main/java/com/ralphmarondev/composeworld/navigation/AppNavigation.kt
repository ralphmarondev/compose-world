package com.ralphmarondev.composeworld.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.auth.login.presentation.LoginScreen
import com.ralphmarondev.auth.register.presentation.RegistrationScreen
import com.ralphmarondev.composeworld.home.presentation.HomeScreen
import com.ralphmarondev.composeworld.ui.theme.ComposeWorldTheme
import com.ralphmarondev.core.data.local.preferences.AppPreferences
import com.ralphmarondev.onboarding.presentation.OnboardingScreen

@Composable
fun AppNavigation(
    preferences: AppPreferences
) {
    val navController = rememberNavController()
    val startDestination: Any = when (preferences.isFirstLaunch()) {
        true -> Routes.Onboarding
        else -> Routes.Login
    }

    var darkTheme by remember { mutableStateOf(preferences.isDarkTheme()) }

    ComposeWorldTheme(darkTheme = darkTheme) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<Routes.Onboarding> {
                OnboardingScreen(
                    onTryComposeWorld = {
                        navController.navigate(Routes.Home) {
                            popUpTo<Routes.Onboarding> { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    onInstallComposeWorld = {
                        navController.navigate(Routes.Register) {
                            popUpTo<Routes.Onboarding> { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<Routes.Register> {
                RegistrationScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onRegistrationSuccessful = {
                        preferences.setIsFirstLaunchDone()
                        navController.navigate(Routes.Login) {
                            popUpTo<Routes.Register> { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<Routes.Login> {
                LoginScreen(
                    onLoginSuccessful = {
                        navController.navigate(Routes.Home) {
                            popUpTo<Routes.Login> { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<Routes.Home> {
                HomeScreen()
            }
        }
    }
}