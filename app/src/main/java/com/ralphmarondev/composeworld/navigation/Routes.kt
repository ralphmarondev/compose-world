package com.ralphmarondev.composeworld.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Onboarding

    @Serializable
    data object Home

    @Serializable
    data object Notes

    @Serializable
    data object Browser

    @Serializable
    data object Calculator

    @Serializable
    data object Settings

    @Serializable
    data object Keeper

    @Serializable
    data object Clock

    @Serializable
    data object Weather
}