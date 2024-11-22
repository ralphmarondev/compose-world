package com.ralphmarondev.keepr.navigation

import kotlinx.serialization.Serializable

object Routes {
    @Serializable
    data object Auth

    @Serializable
    data object Home

    @Serializable
    data class SubCategories(val category: String)

    @Serializable
    data class Details(val subCategory: String)
}