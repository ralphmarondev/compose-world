package com.ralphmarondev.user_settings.data.repository

import com.ralphmarondev.user_settings.data.local.database.dao.UserSettingDao
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository

class UserSettingRepositoryImpl(
    private val userSettingDao: UserSettingDao
) : UserSettingRepository {
    override suspend fun createUser(user: User) {
        userSettingDao.createUser(user)
    }

    override suspend fun updateUser(user: User) {
        userSettingDao.updateUser(user)
    }

    override suspend fun deleteUser(id: Int) {
        userSettingDao.deleteUser(id)
    }

    override suspend fun getUserDetailByUsername(username: String): User {
        return userSettingDao.getUserDetailByUsername(username)
    }

    override suspend fun isUserExists(username: String, password: String): Boolean {
        return userSettingDao.isUserExists(username, password) > 0
    }
}