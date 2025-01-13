package com.ralphmarondev.settings.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Home

    @Serializable
    data object AccountSettings

    object General {
        @Serializable
        data object VersionAndUpdate

        @Serializable
        data object BackupAndRestore

        @Serializable
        data object AppTheme

        @Serializable
        data object Feedback
    }

    object Misc {
        @Serializable
        data object TermsOfService

        @Serializable
        data object OpenSourceLicenses

        @Serializable
        data object AboutDeveloper
    }
}