package com.ralphmarondev.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Home

    @Serializable
    data object Notes

    @Serializable
    data object NewNote

    @Serializable
    data object Browser

    @Serializable
    data object Calculator
}