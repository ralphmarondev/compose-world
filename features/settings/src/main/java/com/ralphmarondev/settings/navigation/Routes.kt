package com.ralphmarondev.settings.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Home

    @Serializable
    data object AccountSettings

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

    object Misc{
        @Serializable
        data object Developer
    }
}