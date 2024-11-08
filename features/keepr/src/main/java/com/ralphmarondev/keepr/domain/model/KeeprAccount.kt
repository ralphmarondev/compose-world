package com.ralphmarondev.keepr.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ralphmarondev.keepr.util.getCurrentDateInString

// update this!
@Entity
data class KeeprAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String, // ex. social
    val subCategory: String, // ex. tiktok
    val usernameOrEmail: String,
    val password: String, // encrypt this later
    val owner: String,
    val createDate: String = getCurrentDateInString(),
    val isDeleted: Boolean = false
)