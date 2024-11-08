package com.ralphmarondev.keepr.data.local

import androidx.room.Dao
import androidx.room.Upsert
import com.ralphmarondev.keepr.domain.model.KeeprUser

@Dao
interface KeeprDao {

    @Upsert
    suspend fun createUser(keeprUser: KeeprUser)
}