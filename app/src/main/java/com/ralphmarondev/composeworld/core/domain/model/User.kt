package com.ralphmarondev.composeworld.core.domain.model

data class User(
    val id: Int = 0,
    val username: String,
    val password: String,
    val fullName: String? = null,
    val passwordHint: String? = null,
    val profilePath: String? = null
)
