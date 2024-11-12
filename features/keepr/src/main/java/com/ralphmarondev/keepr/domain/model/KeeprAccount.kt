package com.ralphmarondev.keepr.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ralphmarondev.keepr.util.getCurrentDateInString

@Entity
data class KeeprAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: Int, // ex. social
    val subCategory: Int, // ex. tiktok
    val name: String = "Account",
    val usernameOrEmail: String,
    val password: String, // encrypt this later
    val owner: String, // current user
    val createDate: String = getCurrentDateInString(),
    val isDeleted: Boolean = false
)


@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)

@Entity
data class SubCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: Int // Category ID
)
