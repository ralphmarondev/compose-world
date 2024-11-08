package com.ralphmarondev.keepr.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ralphmarondev.keepr.util.getCurrentDateInString

@Entity
data class KeeprUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullName: String,
    val username: String,
    val password: String,
    val dateCreated: String = getCurrentDateInString(),
    val isDeleted: Boolean = false
)