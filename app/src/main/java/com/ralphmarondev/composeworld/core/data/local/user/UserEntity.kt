package com.ralphmarondev.composeworld.core.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val fullName: String? = null,
    val passwordHint: String? = null,
    val profilePath: String? = null
)