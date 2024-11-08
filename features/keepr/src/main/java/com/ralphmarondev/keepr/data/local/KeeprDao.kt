package com.ralphmarondev.keepr.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ralphmarondev.keepr.domain.model.KeeprUser

@Dao
interface KeeprDao {

    @Upsert
    suspend fun createUser(keeprUser: KeeprUser)

    @Query("SELECT COUNT(*) > 0 FROM KeeprUser WHERE username=:username AND password=:password and isDeleted=0")
    suspend fun login(username: String, password: String): Boolean
}