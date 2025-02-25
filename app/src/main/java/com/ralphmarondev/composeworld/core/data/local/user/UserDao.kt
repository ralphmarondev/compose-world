package com.ralphmarondev.composeworld.core.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("DELETE FROM users WHERE username = :username")
    suspend fun deleteUserByUsername(username: String)

    @Query("SELECT COUNT(*) FROM users")
    suspend fun isUserTableEmpty(): Int

    @Query("SELECT EXISTS(SELECT * FROM users WHERE username = :username AND password = :password)")
    suspend fun isUserExists(username: String, password: String): Boolean
}