package com.ralphmarondev.composeworld.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Onboarding

    @Serializable
    data object Register

    @Serializable
    data object Login

    @Serializable
    data object Home

    @Serializable
    data object Settings

    @Serializable
    data object Notes

    @Serializable
    data object Calculator
}