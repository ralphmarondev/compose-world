package com.ralphmarondev.user_settings.data.local.preferences

import android.content.Context

class UserSettingsPreferences(
    private val context: Context,
) {
    companion object {
        private const val PREFERENCE_NAME = "user_settings_preferences"
        private const val SAVED_USERNAME = "saved_username"
        private const val ENABLE_AUTH = "enable_auth"
    }

    private val sharedPreferences = context.getSharedPreferences(
        PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    fun setSavedUsername(username: String) {
        sharedPreferences.edit().putString(SAVED_USERNAME, username).apply()
    }

    fun getSavedUsername(): String? {
        return sharedPreferences.getString(SAVED_USERNAME, null)
    }

    fun setEnableAuth(value: Boolean) {
        sharedPreferences.edit().putBoolean(ENABLE_AUTH, value).apply()
    }

    fun getEnableAuth(): Boolean {
        return sharedPreferences.getBoolean(ENABLE_AUTH, true)
    }
}