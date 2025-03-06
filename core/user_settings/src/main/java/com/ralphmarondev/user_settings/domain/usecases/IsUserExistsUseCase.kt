package com.ralphmarondev.user_settings.domain.usecases

import android.util.Log
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository

class IsUserExistsUseCase(
    private val repository: UserSettingRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return try {
            repository.isUserExists(username, password)
        } catch (e: Exception) {
            Log.e("App", "IsUserExists error: ${e.message}")
            false
        }
    }
}