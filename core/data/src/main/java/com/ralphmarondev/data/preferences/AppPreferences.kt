package com.ralphmarondev.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class AppPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME, Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFERENCES_NAME = "AppPrefs"
        private const val KEY_FIRST_LAUNCH = "isFirstLaunch"
        private const val APP_THEME = "app_theme"
        private const val CURRENT_USER = "current_user"
    }

    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setFirstLaunchDone() {
        sharedPreferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }

    fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(APP_THEME, false)
    }

    fun toggleDarkTheme() {
        Log.d("AppPreferences", "Current Theme: ${sharedPreferences.getBoolean(APP_THEME, false)}")
        sharedPreferences.edit()
            .putBoolean(APP_THEME, !sharedPreferences.getBoolean(APP_THEME, false)).apply()
    }

    fun setCurrentUser(value: String) {
        sharedPreferences.edit().putString(CURRENT_USER, value).apply()
    }

    fun getCurrentUser(): String {
        return sharedPreferences.getString(CURRENT_USER, "no_user")!!
    }
}