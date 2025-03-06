package com.ralphmarondev.settings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.settings.presentation.account.AccountSettingScreen
import com.ralphmarondev.settings.presentation.general.backup.BackupAndRestoreScreen
import com.ralphmarondev.settings.presentation.general.feedback.FeedbackScreen
import com.ralphmarondev.settings.presentation.general.theme.AppThemeScreen
import com.ralphmarondev.settings.presentation.general.version.AppVersionScreen
import com.ralphmarondev.settings.presentation.home.HomeScreen
import com.ralphmarondev.settings.presentation.misc.developer.AboutDeveloperScreen
import com.ralphmarondev.settings.presentation.misc.license.OpenSourceLicensesScreen
import com.ralphmarondev.settings.presentation.misc.terms.TermsOfServiceScreen
import com.ralphmarondev.user_settings.data.local.database.dao.UserSettingDao

@Composable
fun SettingsNavigation(
    darkTheme: Boolean,
    dao: UserSettingDao,
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
                navigateToVersion = {
                    navController.navigate(Routes.General.VersionAndUpdate) {
                        launchSingleTop = true
                    }
                },
                navigateToBackup = {
                    navController.navigate(Routes.General.BackupAndRestore) {
                        launchSingleTop = true
                    }
                },
                navigateToAppTheme = {
                    navController.navigate(Routes.General.AppTheme) {
                        launchSingleTop = true
                    }
                },
                navigateToFeedback = {
                    navController.navigate(Routes.General.Feedback) {
                        launchSingleTop = true
                    }
                },
                // misc
                navigateToTermsOfService = {
                    navController.navigate(Routes.Misc.TermsOfService) {
                        launchSingleTop = true
                    }
                },
                navigateToOpenSourceLicenses = {
                    navController.navigate(Routes.Misc.OpenSourceLicenses) {
                        launchSingleTop = true
                    }
                },
                navigateToDeveloper = {
                    navController.navigate(Routes.Misc.AboutDeveloper) {
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
        composable<Routes.General.VersionAndUpdate> {
            AppVersionScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
        composable<Routes.General.BackupAndRestore> {
            BackupAndRestoreScreen(
                navigateBack = { navController.navigateUp() }
            )
        }
        composable<Routes.General.AppTheme> {
            AppThemeScreen(
                navigateBack = { navController.navigateUp() },
                darkTheme = darkTheme,
                toggleDarkTheme = toggleDarkTheme
            )
        }
        composable<Routes.General.Feedback> {
            FeedbackScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Misc.TermsOfService> {
            TermsOfServiceScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Misc.OpenSourceLicenses> {
            OpenSourceLicensesScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Misc.AboutDeveloper> {
            AboutDeveloperScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}