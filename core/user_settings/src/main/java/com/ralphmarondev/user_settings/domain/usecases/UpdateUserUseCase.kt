package com.ralphmarondev.user_settings.domain.usecases

import android.util.Log
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository

class UpdateUserUseCase(
    private val repository: UserSettingRepository
) {
    suspend operator fun invoke(user: User) {
        try {
            repository.updateUser(user)
            Log.d("App", "User updated successfully!")
        } catch (e: Exception) {
            Log.e("App", "Error updating user: ${e.message}")
        }
    }
}