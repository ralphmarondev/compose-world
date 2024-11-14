package com.ralphmarondev.keepr.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ralphmarondev.keepr.util.getCurrentDateInString

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val subCategoryName: String,
    val name: String,
    val username: String,
    val password: String, // encrypt this later
    val createDate: String = getCurrentDateInString(),
    val isDeleted: Boolean = false
)
