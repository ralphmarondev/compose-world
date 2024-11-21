package com.ralphmarondev.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val username: String,
    val password: String, // encrypt this later
)