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
import com.ralphmarondev.settings.presentation.misc.developer.AboutDeveloperScreen
import com.ralphmarondev.settings.presentation.misc.license.OpenSourceLicensesScreen
import com.ralphmarondev.settings.presentation.misc.terms.TermsOfServiceScreen
import com.ralphmarondev.settings.presentation.overview.OverviewScreen

@Composable
fun SettingsNavigation(
    navigateBack: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            OverviewScreen(
                navController = navController,
                navigateBack = navigateBack
            )
        }
        composable<Routes.AccountSettings> {
            AccountSettingScreen(
                navigateBack = {
                    navController.navigateUp()
                }
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
                navigateBack = { navController.navigateUp() }
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