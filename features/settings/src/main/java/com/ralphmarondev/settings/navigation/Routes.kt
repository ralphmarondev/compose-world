package com.ralphmarondev.settings.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Home

    object General {
        @Serializable
        data object Language

        @Serializable
        data object Version

        @Serializable
        data object Backup

        @Serializable
        data object ClearData
    }

    object Appearance {
        @Serializable
        data object AppTheme

        @Serializable
        data object FontStyleAndSize
    }
}