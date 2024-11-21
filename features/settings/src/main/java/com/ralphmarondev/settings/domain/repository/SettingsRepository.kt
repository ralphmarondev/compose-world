package com.ralphmarondev.settings.domain.repository

import com.ralphmarondev.data.user.User

interface SettingsRepository {
    suspend fun getCurrentUserDetails(username: String): User
}