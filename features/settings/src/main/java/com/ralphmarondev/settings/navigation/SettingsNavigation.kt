package com.ralphmarondev.settings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.data.user.UserDao
import com.ralphmarondev.settings.presentation.account.AccountSettingScreen
import com.ralphmarondev.settings.presentation.appearance.fonts.AppFontStyleScreen
import com.ralphmarondev.settings.presentation.appearance.theme.AppThemeScreen
import com.ralphmarondev.settings.presentation.general.backup.BackupAndRestoreScreen
import com.ralphmarondev.settings.presentation.general.data.ClearDataScreen
import com.ralphmarondev.settings.presentation.general.language.AppLanguageScreen
import com.ralphmarondev.settings.presentation.general.version.AppVersionScreen
import com.ralphmarondev.settings.presentation.home.HomeScreen
import com.ralphmarondev.settings.presentation.misc.developer.AboutDeveloperScreen

@Composable
fun SettingsNavigation(
    darkTheme: Boolean,
    dao: UserDao,
    currentUser: String,
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
                navigateToAccountSettings = {
                    navController.navigate(Routes.AccountSettings) {
                        launchSingleTop = true
                    }
                },
                // general
                navigateToLanguage = {
                    navController.navigate(Routes.General.Language) {
                        launchSingleTop = true
                    }
                },
                navigateToVersion = {
                    navController.navigate(Routes.General.Version) {
                        launchSingleTop = true
                    }
                },
                navigateToBackup = {
                    navController.navigate(Routes.General.Backup) {
                        launchSingleTop = true
                    }
                },
                navigateToClearData = {
                    navController.navigate(Routes.General.ClearData) {
                        launchSingleTop = true
                    }
                },
                // appearance
                navigateToAppTheme = {
                    navController.navigate(Routes.Appearance.AppTheme) {
                        launchSingleTop = true
                    }
                },
                navigateToAppFontStyle = {
                    navController.navigate(Routes.Appearance.FontStyleAndSize) {
                        launchSingleTop = true
                    }
                },
                // misc
                navigateToDeveloper = {
                    navController.navigate(Routes.Misc.Developer) {
                        launchSingleTop = true
                    }
                },
                dao = dao,
                currentUser = currentUser
            )
        }

        composable<Routes.AccountSettings> {
            AccountSettingScreen(
                navigateBack = {
                    navController.navigateUp()
                },
                currentUser = currentUser,
                dao = dao
            )
        }

        composable<Routes.General.Language> {
            AppLanguageScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
        composable<Routes.General.Version> {
            AppVersionScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
        composable<Routes.General.Backup> {
            BackupAndRestoreScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
        composable<Routes.General.ClearData> {
            ClearDataScreen(
                navigateBack = { navController.navigateUp() }
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

        composable<Routes.Misc.Developer> {
            AboutDeveloperScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}