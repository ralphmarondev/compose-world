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
import com.ralphmarondev.keepr.presentation.subcategories.SubCategories

@Composable
fun KeeprNavigation(
    navigateBack: () -> Unit,
    keeprDao: KeeprDao,
    preferences: KeeprPreferences
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Auth
    ) {
        composable<Routes.Auth> {
            AuthScreen(
                navigateBack = navigateBack,
                navigateToHome = { username ->
                    navController.navigate(Routes.Home(username))
                },
                currentUser = "ralphmaron"
            )
        }
        composable<Routes.Home> {
            val args = it.toRoute<Routes.Home>()
            HomeScreen(
                currentUser = args.username,
                logout = {
                    navController.navigateUp()
                },
                navigateToSubCategory = { category ->
                    navController.navigate(Routes.SubCategories(category))
                },
                keeprDao = keeprDao,
                preferences = preferences
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
    }
}