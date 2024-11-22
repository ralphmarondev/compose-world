package com.ralphmarondev.keepr.data.local

import android.content.Context
import android.content.SharedPreferences

class KeeprPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME, Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFERENCES_NAME = "KeeprPrefs"
        private const val KEY_FIRST_LAUNCH = "isKeeprFirstLaunch"
        private const val ENABLE_AUTH = "enableKeeprAuth"
    }

    fun isKeeprFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setKeeprFirstLaunchDone() {
        sharedPreferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }

    fun isKeeprAuthEnabled(): Boolean {
        return sharedPreferences.getBoolean(ENABLE_AUTH, false)
    }

    fun toggleKeeprAuth() {
        sharedPreferences.edit()
            .putBoolean(ENABLE_AUTH, !sharedPreferences.getBoolean(ENABLE_AUTH, false)).apply()
    }
}