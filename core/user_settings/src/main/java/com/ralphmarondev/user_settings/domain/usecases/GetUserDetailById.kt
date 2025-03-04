package com.ralphmarondev.user_settings.domain.usecases

import android.util.Log
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository

class GetUserDetailById(
    private val repository: UserSettingRepository
) {
    suspend operator fun invoke(id: Int): User {
        return try {
            val result = repository.getUserDetail(id)
            Log.d("App", "Success getting user detail")
            result
        } catch (e: Exception) {
            Log.e("App", "Error getting user detail")
            User(id = -2, username = "error", password = "error", fullName = "error")
        }
    }
}