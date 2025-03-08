package com.ralphmarondev.user_settings.domain.usecases

import android.util.Log
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository

class GetUserDetailByUsername(
    private val repository: UserSettingRepository
) {
    suspend operator fun invoke(username: String): User {
        return try {
            val result = repository.getUserDetailByUsername(username)
            Log.d("App", "Success getting user detail")
            result
        } catch (e: Exception) {
            Log.e("App", "Error getting user detail")
            User(id = -1, username = "error", password = "error", fullName = "error")
        }
    }
}