package com.ralphmarondev.settings.data.repository

import com.ralphmarondev.data.user.User
import com.ralphmarondev.data.user.UserDao
import com.ralphmarondev.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val dao: UserDao) : SettingsRepository {
    override suspend fun getCurrentUserDetails(username: String): User {
        return dao.getUserByUsername(username)
    }
}