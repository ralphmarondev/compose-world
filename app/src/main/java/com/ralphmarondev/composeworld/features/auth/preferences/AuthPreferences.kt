package com.ralphmarondev.composeworld.features.auth.preferences

import android.content.Context
import android.content.SharedPreferences

class AuthPreferences(
    private val context: Context
) {
    companion object {
        private const val AUTH_PREFS = "auth_prefs"
        private const val REMEMBER_ME = "is_remember_me_checked"
        private const val SAVED_USERNAME = "saved_username"
        private const val SAVED_PASSWORD = "saved_password"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        AUTH_PREFS, Context.MODE_PRIVATE
    )

    fun isRememberMeChecked(): Boolean {
        return sharedPreferences.getBoolean(REMEMBER_ME, true)
    }

    fun toggleRememberMe() {
        sharedPreferences.edit().putBoolean(REMEMBER_ME, !isRememberMeChecked()).apply()
    }

    fun setUsernameToRemember(value: String) {
        sharedPreferences.edit().putString(SAVED_USERNAME, value).apply()
    }

    fun getRememberedUsername(): String? {
        return sharedPreferences.getString(SAVED_USERNAME, null)
    }

    fun setPasswordToRemember(value: String) {
        sharedPreferences.edit().putString(SAVED_PASSWORD, value).apply()
    }

    fun getRememberedPassword(): String? {
        return sharedPreferences.getString(SAVED_PASSWORD, null)
    }
}