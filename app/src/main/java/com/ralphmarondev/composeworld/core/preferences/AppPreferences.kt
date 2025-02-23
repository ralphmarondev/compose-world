package com.ralphmarondev.composeworld.core.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(
    private val context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME, Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFERENCES_NAME = "app_preferences"
        private const val FIRST_LAUNCH = "first_launch"
        private const val DARK_THEME = "dark_theme"
    }

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true)
    }

    fun setFirstLaunchAsDone() {
        sharedPreferences.edit().putBoolean(FIRST_LAUNCH, false).apply()
    }

    fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(DARK_THEME, false)
    }

    fun toggleDarkTheme() {
        sharedPreferences.edit().putBoolean(DARK_THEME, !isDarkTheme()).apply()
    }
}