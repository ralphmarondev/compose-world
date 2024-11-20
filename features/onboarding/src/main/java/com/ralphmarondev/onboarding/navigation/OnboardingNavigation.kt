package com.ralphmarondev.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.onboarding.presentation.home.HomeScreen
import com.ralphmarondev.onboarding.presentation.register.RegistrationScreen

@Composable
fun OnBoardingNavigation(
    navigateToHome: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                register = {
                    navController.navigate(Routes.Register)
                }
            )
        }
        composable<Routes.Register> {
            RegistrationScreen(
                finished = navigateToHome,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}