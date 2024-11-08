package com.ralphmarondev.keepr.domain.repository

import com.ralphmarondev.keepr.domain.model.KeeprUser

interface KeeprRepository {
    suspend fun createUser(keeprUser: KeeprUser)
}