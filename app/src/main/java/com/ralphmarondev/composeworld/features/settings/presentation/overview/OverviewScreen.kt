package com.ralphmarondev.composeworld.features.settings.presentation.overview

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.DeveloperBoard
import androidx.compose.material.icons.outlined.MiscellaneousServices
import androidx.compose.material.icons.outlined.Source
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ralphmarondev.composeworld.core.domain.model.User
import com.ralphmarondev.composeworld.features.settings.navigation.SettingsRoute
import com.ralphmarondev.composeworld.features.settings.presentation.overview.components.AccountCard
import com.ralphmarondev.composeworld.features.settings.presentation.overview.components.HomeTopBar
import com.ralphmarondev.composeworld.features.settings.presentation.overview.components.SettingsItemCard
import com.ralphmarondev.composeworld.features.settings.presentation.overview.components.SettingsItemCategoryText

@Composable
fun OverviewScreen(
    navController: NavHostController,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopBar(navigateBack)
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                AccountCard(
                    user = User(
                        username = "ralphmaron",
                        password = "iscute"
                    ),
                    onClick = {}
                )
            }
            item {
                SettingsItemCategoryText(text = "General")
                SettingsItemCard(
                    onClick = {
                        navController.navigate(SettingsRoute.Theme) {
                            launchSingleTop = true
                        }
                    },
                    text = "Theme",
                    leadingIcon = Icons.Outlined.DarkMode
                )
            }
            item {
                SettingsItemCategoryText(text = "Misc")
                SettingsItemCard(
                    onClick = {
                        navController.navigate(SettingsRoute.Terms) {
                            launchSingleTop = true
                        }
                    },
                    text = "Terms",
                    leadingIcon = Icons.Outlined.MiscellaneousServices
                )
                SettingsItemCard(
                    onClick = {
                        navController.navigate(SettingsRoute.License) {
                            launchSingleTop = true
                        }
                    },
                    text = "License",
                    leadingIcon = Icons.Outlined.Source
                )
                SettingsItemCard(
                    onClick = {
                        navController.navigate(SettingsRoute.Developer) {
                            launchSingleTop = true
                        }
                    },
                    text = "Developer",
                    leadingIcon = Icons.Outlined.DeveloperBoard
                )
            }
        }
    }
}