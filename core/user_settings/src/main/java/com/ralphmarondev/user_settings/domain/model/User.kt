package com.ralphmarondev.user_settings.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val fullName: String,
    val hintPassword: String? = null,
    val imagePath: String? = null
)
