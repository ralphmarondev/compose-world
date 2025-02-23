package com.ralphmarondev.composeworld.features.settings.navigation

import kotlinx.serialization.Serializable

object SettingsRoute {

    @Serializable
    data object Overview

    @Serializable
    data object Account

    @Serializable
    data object Theme

    @Serializable
    data object Terms

    @Serializable
    data object License

    @Serializable
    data object Developer
}