package com.ralphmarondev.settings.domain.usecases

import android.util.Log
import com.ralphmarondev.data.user.User
import com.ralphmarondev.settings.domain.repository.SettingsRepository

class GetCurrentUserDetailsUseCase(private val repository: SettingsRepository) {
    suspend fun getCurrentUserDetails(username: String): User {
        return try {
            val user = repository.getCurrentUserDetails(username)
            Log.d("GetCurrentUserDetails", "User details fetched successfully")
            user
        } catch (e: Exception) {
            Log.e("GetCurrentUserDetails", "Error fetching user details: ${e.message}")
            User(id = -1, username = username, fullName = "", password = "")
        }
    }
}