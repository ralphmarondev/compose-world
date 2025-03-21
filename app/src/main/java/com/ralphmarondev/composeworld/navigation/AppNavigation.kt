package com.ralphmarondev.composeworld.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.auth.login.presentation.LoginScreen
import com.ralphmarondev.auth.register.presentation.RegistrationScreen
import com.ralphmarondev.calculator.navigation.CalculatorNavigation
import com.ralphmarondev.composeworld.home.presentation.HomeScreen
import com.ralphmarondev.composeworld.ui.theme.ComposeWorldTheme
import com.ralphmarondev.core.data.local.preferences.AppPreferences
import com.ralphmarondev.core.util.LocalThemeState
import com.ralphmarondev.notes.navigation.NotesNavigation
import com.ralphmarondev.onboarding.presentation.OnboardingScreen
import com.ralphmarondev.quirk.navigation.QuirkNavigation
import com.ralphmarondev.settings.navigation.SettingsNavigation
import com.ralphmarondev.user_settings.data.local.preferences.UserSettingsPreferences
import com.ralphmarondev.weather.navigation.WeatherNavigation

@Composable
fun AppNavigation(
    preferences: AppPreferences
) {
    val context = LocalContext.current
    val userSettingsPreferences = UserSettingsPreferences(context)
    val savedUser by remember { mutableStateOf(userSettingsPreferences.getSavedUsername()) }
    val isAuthEnabled by remember { mutableStateOf(userSettingsPreferences.getEnableAuth()) }

    val themeState = LocalThemeState.current

    val navController = rememberNavController()
    val startDestination: Any = when (preferences.isFirstLaunch()) {
        true -> Routes.Onboarding
        else -> {
            if (!isAuthEnabled && savedUser != null) {
                Routes.Home
            } else {
                Routes.Login
            }
        }
    }

    ComposeWorldTheme(darkTheme = themeState.darkTheme.value) {
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
                        if (isAuthEnabled) {
                            navController.navigate(Routes.Login) {
                                popUpTo<Routes.Register> { inclusive = true }
                                launchSingleTop = true
                            }
                        } else {
                            navController.navigate(Routes.Home) {
                                popUpTo<Routes.Register> { inclusive = true }
                                launchSingleTop = true
                            }
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
                HomeScreen(
                    navController = navController
                )
            }
            composable<Routes.Settings> {
                SettingsNavigation(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable<Routes.Notes> {
                NotesNavigation(
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
            composable<Routes.Weather> {
                WeatherNavigation(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable<Routes.Quirk> {
                QuirkNavigation(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}