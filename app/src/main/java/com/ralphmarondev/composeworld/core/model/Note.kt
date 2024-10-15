package com.ralphmarondev.composeworld.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ralphmarondev.composeworld.utils.getCurrentDateInString

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val dateCreated: String = getCurrentDateInString(),
    val isDeleted: Boolean = false
)