package com.ralphmarondev.composeworld.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.composeworld.core.preferences.AppPreferences
import com.ralphmarondev.composeworld.features.auth.presentation.login.LoginScreen
import com.ralphmarondev.composeworld.features.home.presentation.HomeScreen
import com.ralphmarondev.composeworld.features.onboarding.presentation.OnboardingScreen
import com.ralphmarondev.composeworld.ui.theme.ComposeWorldTheme

@Composable
fun AppNavigation(
    preferences: AppPreferences,
    navController: NavHostController = rememberNavController()
) {
    val startDestination: Any = if (preferences.isFirstLaunch()) {
        Routes.Onboarding
    } else {
        Routes.Auth
    }

    var darkTheme by remember { mutableStateOf(preferences.isDarkTheme()) }

    ComposeWorldTheme(
        darkTheme = darkTheme
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable<Routes.Onboarding> {
                OnboardingScreen(
                    onCompleted = {
                        preferences.setFirstLaunchAsDone()
                        navController.navigate(Routes.Auth) {
                            popUpTo<Routes.Onboarding> {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Routes.Auth> {
                LoginScreen(
                    darkTheme = darkTheme,
                    toggleDarkTheme = {
                        preferences.toggleDarkTheme()
                        darkTheme = !darkTheme
                    },
                    onLoginSuccessful = {
                        navController.navigate(Routes.Home) {
                            popUpTo<Routes.Auth> {
                                inclusive = true
                            }
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