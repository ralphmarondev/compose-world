package com.ralphmarondev.composeworld.core.domain.repositories

import com.ralphmarondev.composeworld.core.domain.model.User

interface UserRepository {

    suspend fun createUser(user: User)
    suspend fun getUserByUsername(username: String): User?
    suspend fun deleteUserByUsername(username: String)
    suspend fun isUserTableEmpty(): Boolean
    suspend fun isUserExists(username: String, password: String): Boolean
}