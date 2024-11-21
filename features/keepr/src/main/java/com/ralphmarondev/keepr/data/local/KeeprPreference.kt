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
    }

    fun isKeeprFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setKeeprFirstLaunchDone() {
        sharedPreferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }
}