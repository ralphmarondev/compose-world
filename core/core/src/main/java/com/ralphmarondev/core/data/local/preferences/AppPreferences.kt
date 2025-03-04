package com.ralphmarondev.core.data.local.preferences

import android.content.Context

class AppPreferences(
    private val context: Context
) {
    companion object {
        private const val PREFERENCE_NAME = "app_preferences"
        private const val FIRST_LAUNCH = "first_launch"
        private const val DARK_THEME = "dark_theme"
    }

    private val sharedPreferences = context.getSharedPreferences(
        PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true)
    }

    fun setIsFirstLaunchDone() {
        sharedPreferences.edit().putBoolean(FIRST_LAUNCH, true).apply()
    }

    fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(DARK_THEME, false)
    }

    fun setDarkTheme() {
        sharedPreferences.edit().putBoolean(DARK_THEME, !isDarkTheme()).apply()
    }
}