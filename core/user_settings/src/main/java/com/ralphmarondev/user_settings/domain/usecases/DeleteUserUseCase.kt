package com.ralphmarondev.user_settings.domain.usecases

import android.util.Log
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository

class DeleteUserUseCase(
    private val repository: UserSettingRepository
) {
    suspend operator fun invoke(id: Int) {
        try {
            repository.deleteUser(id)
            Log.d("App", "User deleted successfully!")
        } catch (e: Exception) {
            Log.e("App", "Error deleting user: ${e.message}")
        }
    }
}