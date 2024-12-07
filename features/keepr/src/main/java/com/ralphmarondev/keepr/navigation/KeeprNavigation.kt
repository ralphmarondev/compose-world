package com.ralphmarondev.keepr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.data.local.KeeprPreferences
import com.ralphmarondev.keepr.presentation.auth.AuthScreen
import com.ralphmarondev.keepr.presentation.details.DetailScreen
import com.ralphmarondev.keepr.presentation.home.HomeScreen
import com.ralphmarondev.keepr.presentation.settings.SettingsScreen
import com.ralphmarondev.keepr.presentation.subcategories.SubCategories
import com.ralphmarondev.keepr.presentation.update.UpdateScreen

@Composable
fun KeeprNavigation(
    navigateBack: () -> Unit,
    keeprDao: KeeprDao,
    preferences: KeeprPreferences,
    currentUser: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Auth> {
            AuthScreen(
                navigateBack = navigateBack,
                navigateToHome = {
                    navController.navigate(Routes.Home)
                },
                currentUser = currentUser
            )
        }
        composable<Routes.Home> {
            HomeScreen(
                keeprDao = keeprDao,
                preferences = preferences,
                navigateBack = navigateBack,
                navigateToUpdate = { title ->
                    navController.navigate(Routes.Update(title)) {
                        launchSingleTop = true
                    }
                },
                navigateToSubCategory = { category ->
                    navController.navigate(Routes.SubCategories(category))
                }
            )
        }
        composable<Routes.SubCategories> {
            val args = it.toRoute<Routes.SubCategories>()
            SubCategories(
                categoryName = args.category,
                backToHome = {
                    navController.navigateUp()
                },
                navigateToDetails = { subCategory ->
                    navController.navigate(Routes.Details(subCategory))
                },
                keeprDao = keeprDao
            )
        }
        composable<Routes.Details> {
            val args = it.toRoute<Routes.Details>()
            DetailScreen(
                subCategoryName = args.subCategory,
                backToSubCategories = {
                    navController.navigateUp()
                },
                keeprDao = keeprDao
            )
        }
        composable<Routes.Settings> {
            SettingsScreen(
                preferences = preferences,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<Routes.Update> {
            val args = it.toRoute<Routes.Update>()
            UpdateScreen(
                title = args.title,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}