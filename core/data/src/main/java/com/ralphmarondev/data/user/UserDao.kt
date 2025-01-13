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

    @Query("SELECT * FROM User WHERE username=:username LIMIT 1")
    suspend fun getUserByUsername(username: String): User

    @Query("UPDATE User SET fullName = :newFullName WHERE id = :id")
    suspend fun updateFullNameById(newFullName: String, id: Int)

    @Query("UPDATE User SET username = :newUsername WHERE id = :id")
    suspend fun updateUserNameById(newUsername: String, id: Int)

    @Query("UPDATE User SET password = :newPassword WHERE id = :id")
    suspend fun updatePasswordById(newPassword: String, id: Int)
}