package com.ralphmarondev.keepr.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ralphmarondev.keepr.util.getCurrentDateInString

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categoryName: String, // ex. social
    val subCategoryName: String, // ex. tiktok
    val username: String,
    val password: String, // encrypt this later
    val createDate: String = getCurrentDateInString(),
    val isDeleted: Boolean = false
)
