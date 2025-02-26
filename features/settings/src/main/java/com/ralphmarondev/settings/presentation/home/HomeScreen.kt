package com.ralphmarondev.settings.presentation.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Backup
import androidx.compose.material.icons.outlined.DeveloperBoard
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.MiscellaneousServices
import androidx.compose.material.icons.outlined.SettingsApplications
import androidx.compose.material.icons.outlined.Source
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.data.user.UserDao
import com.ralphmarondev.settings.presentation.home.components.AccountCard
import com.ralphmarondev.settings.presentation.home.components.HomeTopBar
import com.ralphmarondev.settings.presentation.home.components.SettingsItemCard
import com.ralphmarondev.settings.presentation.home.components.SettingsItemCategoryText

@Composable
fun HomeScreen(
    dao: UserDao,
    currentUser: String,
    navigateBack: () -> Unit,
    navigateToAccountSettings: () -> Unit,
    // general
    navigateToVersion: () -> Unit,
    navigateToBackup: () -> Unit,
    navigateToAppTheme: () -> Unit,
    navigateToFeedback: () -> Unit,
    // misc
    navigateToTermsOfService: () -> Unit,
    navigateToOpenSourceLicenses: () -> Unit,
    navigateToDeveloper: () -> Unit
) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            dao = dao,
            username = currentUser
        )
    )

    val user by viewModel.currentUser.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(navigateBack)
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
                    onClick = navigateToAccountSettings
                )
            }

            item {
                SettingsItemCategoryText(text = "General")
                SettingsItemCard(
                    onClick = navigateToVersion,
                    text = "Version and Update",
                    leadingIcon = Icons.Outlined.Update
                )
                SettingsItemCard(
                    onClick = navigateToBackup,
                    text = "Backup and Restore",
                    leadingIcon = Icons.Outlined.Backup
                )
                SettingsItemCard(
                    onClick = navigateToAppTheme,
                    text = "App Theme",
                    leadingIcon = Icons.Outlined.SettingsApplications
                )
                SettingsItemCard(
                    onClick = navigateToFeedback,
                    text = "Feedback",
                    leadingIcon = Icons.Outlined.Feedback
                )
            }

            item {
                SettingsItemCategoryText(text = "Miscellaneous")
                SettingsItemCard(
                    onClick = navigateToTermsOfService,
                    text = "Terms of Service",
                    leadingIcon = Icons.Outlined.MiscellaneousServices
                )
                SettingsItemCard(
                    onClick = navigateToOpenSourceLicenses,
                    text = "Open Source Licenses",
                    leadingIcon = Icons.Outlined.Source
                )
                SettingsItemCard(
                    onClick = navigateToDeveloper,
                    text = "About Developer",
                    leadingIcon = Icons.Outlined.DeveloperBoard
                )
            }
            item { Spacer(modifier = Modifier.height(160.dp)) }
        }
    }
}