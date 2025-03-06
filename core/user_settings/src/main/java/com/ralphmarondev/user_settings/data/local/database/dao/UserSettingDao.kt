package com.ralphmarondev.user_settings.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ralphmarondev.user_settings.domain.model.User

@Dao
interface UserSettingDao {

    @Upsert
    suspend fun createUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Query("SELECT * FROM user WHERE id = :id LIMIT 1")
    suspend fun getUserDetail(id: Int): User

    @Query("SELECT COUNT(*) FROM user where username = :username AND password = :password")
    suspend fun isUserExists(username: String, password: String): Int
}