package com.ralphmarondev.data.user

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun register(user: User)

    @Query("SELECT COUNT(*) > 0 FROM User WHERE username=:username AND password=:password")
    suspend fun login(username: String, password: String): Boolean
}