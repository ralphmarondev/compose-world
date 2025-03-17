package com.ralphmarondev.settings.presentation.overview

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backup
import androidx.compose.material.icons.outlined.DeveloperBoard
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.MiscellaneousServices
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SettingsApplications
import androidx.compose.material.icons.outlined.Source
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ralphmarondev.settings.navigation.Routes
import com.ralphmarondev.settings.presentation.overview.components.AccountCard
import com.ralphmarondev.settings.presentation.overview.components.HomeTopBar
import com.ralphmarondev.settings.presentation.overview.components.SettingsItemCard
import com.ralphmarondev.settings.presentation.overview.components.SettingsItemCategoryText
import org.koin.androidx.compose.koinViewModel

@Composable
fun OverviewScreen(
    navController: NavHostController,
    navigateBack: () -> Unit
) {
    val viewModel: OverviewViewModel = koinViewModel()
    val user by viewModel.currentUser.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(navigateBack = navigateBack)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                AccountCard(
                    user = user,
                    onClick = {
                        navController.navigate(Routes.AccountSettings) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            item {
                SettingsItemCategoryText(text = "General")
                SettingsItemCard(
                    onClick = {
                        navController.navigate(Routes.General.VersionAndUpdate) {
                            launchSingleTop = true
                        }
                    },
                    text = "Version and Update",
                    leadingIcon = Icons.Outlined.Update
                )
                SettingsItemCard(
                    onClick = {
                        navController.navigate(Routes.General.BackupAndRestore) {
                            launchSingleTop = true
                        }
                    },
                    text = "Backup and Restore",
                    leadingIcon = Icons.Outlined.Backup
                )
                SettingsItemCard(
                    onClick = {
                        navController.navigate(Routes.General.AppTheme) {
                            launchSingleTop = true
                        }
                    },
                    text = "App Theme",
                    leadingIcon = Icons.Outlined.SettingsApplications
                )
                SettingsItemCard(
                    onClick = {},
                    text = "Security and Privacy",
                    leadingIcon = Icons.Outlined.Security
                )
                SettingsItemCard(
                    onClick = {
                        navController.navigate(Routes.General.Feedback) {
                            launchSingleTop = true
                        }
                    },
                    text = "Feedback",
                    leadingIcon = Icons.Outlined.Feedback
                )
            }

            item {
                SettingsItemCategoryText(text = "Miscellaneous")
                SettingsItemCard(
                    onClick = {
                        navController.navigate(Routes.Misc.TermsOfService) {
                            launchSingleTop = true
                        }
                    },
                    text = "Terms of Service",
                    leadingIcon = Icons.Outlined.MiscellaneousServices
                )
                SettingsItemCard(
                    onClick = {
                        navController.navigate(Routes.Misc.OpenSourceLicenses) {
                            launchSingleTop = true
                        }
                    },
                    text = "Open Source Licenses",
                    leadingIcon = Icons.Outlined.Source
                )
                SettingsItemCard(
                    onClick = {
                        navController.navigate(Routes.Misc.AboutDeveloper) {
                            launchSingleTop = true
                        }
                    },
                    text = "About Developer",
                    leadingIcon = Icons.Outlined.DeveloperBoard
                )
            }
            item { Spacer(modifier = Modifier.height(160.dp)) }
        }
    }
}