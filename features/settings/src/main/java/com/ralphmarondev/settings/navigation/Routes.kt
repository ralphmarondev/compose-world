package com.ralphmarondev.settings.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Home

    @Serializable
    data object ComingSoon

    object General{
        @Serializable
        data object Language
    }

    object Appearance{
        @Serializable
        data object AppTheme

        @Serializable
        data object FontStyleAndSize
    }
}