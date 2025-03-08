package com.ralphmarondev.user_settings.domain.repository

import com.ralphmarondev.user_settings.domain.model.User

interface UserSettingRepository {
    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(id: Int)
    suspend fun getUserDetailByUsername(username: String): User
    suspend fun isUserExists(username: String, password: String): Boolean
}