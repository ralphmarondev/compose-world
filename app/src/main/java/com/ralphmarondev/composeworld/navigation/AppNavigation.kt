package com.ralphmarondev.composeworld.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.auth.register.presentation.RegistrationScreen
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
                            launchSingleTop = true
                        }
                    },
                    onInstallComposeWorld = {
                        navController.navigate(Routes.Register) {
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
                        navController.navigate(Routes.Login) {
                            popUpTo<Routes.Register> { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable<Routes.Login> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Auth"
                    )
                }
            }
            composable<Routes.Home> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Home"
                    )
                }
            }
        }
    }
}