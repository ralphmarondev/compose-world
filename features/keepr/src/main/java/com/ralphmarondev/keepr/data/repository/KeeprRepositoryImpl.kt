package com.ralphmarondev.keepr.data.repository

import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.domain.model.KeeprUser
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class KeeprRepositoryImpl(
    private val keeprDao: KeeprDao
) : KeeprRepository {
    override suspend fun createUser(keeprUser: KeeprUser) {
        keeprDao.createUser(keeprUser)
    }

    override suspend fun login(username: String, password: String): Boolean {
        return keeprDao.login(
            username = username,
            password = password
        )
    }
}