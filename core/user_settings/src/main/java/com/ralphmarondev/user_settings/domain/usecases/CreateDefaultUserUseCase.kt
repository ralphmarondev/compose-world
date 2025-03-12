package com.ralphmarondev.user_settings.domain.usecases

import android.util.Log
import com.ralphmarondev.user_settings.data.local.preferences.UserSettingsPreferences
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository

class CreateDefaultUserUseCase(
    private val repository: UserSettingRepository,
    private val userSettingsPreferences: UserSettingsPreferences
) {
    suspend operator fun invoke() {
        try {
            val user = User(
                id = -1,
                fullName = "Compose World User",
                username = "compose",
                password = "world"
            )
            repository.createUser(user)
            userSettingsPreferences.setSavedUsername(user.username)
            Log.d("App", "Default user created successfully!")
        } catch (e: Exception) {
            Log.e("App", "Error creating default user: ${e.message}")
        }
    }
}