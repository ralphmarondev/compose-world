package com.ralphmarondev.user_settings.domain.usecases

import android.util.Log
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository

class CreateUserUseCase(
    private val repository: UserSettingRepository
) {
    suspend operator fun invoke(user: User) {
        try {
            repository.createUser(user)
            Log.d("App", "User created successfully!")
        } catch (e: Exception) {
            Log.e("App", "Error creating user: ${e.message}")
        }
    }
}