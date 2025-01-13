package com.ralphmarondev.settings.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Home

    @Serializable
    data object AccountSettings

    object General {
        @Serializable
        data object Version

        @Serializable
        data object Backup
    }

    object Appearance {
        @Serializable
        data object AppTheme
    }

    object Misc{
        @Serializable
        data object Developer
    }
}