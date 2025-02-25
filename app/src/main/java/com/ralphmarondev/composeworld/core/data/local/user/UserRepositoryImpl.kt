package com.ralphmarondev.composeworld.core.data.local.user

import com.ralphmarondev.composeworld.core.domain.model.User
import com.ralphmarondev.composeworld.core.domain.repositories.UserRepository
import com.ralphmarondev.composeworld.core.util.toDomain
import com.ralphmarondev.composeworld.core.util.toEntity

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun createUser(user: User) {
        userDao.createUser(user.toEntity())
    }

    override suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)?.toDomain()
    }

    override suspend fun deleteUserByUsername(username: String) {
        userDao.deleteUserByUsername(username)
    }

    override suspend fun isUserTableEmpty(): Boolean {
        return userDao.isUserTableEmpty() == 0
    }

    override suspend fun isUserExists(username: String, password: String): Boolean {
        return userDao.isUserExists(
            username = username,
            password = password
        )
    }
}