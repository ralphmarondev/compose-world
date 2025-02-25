package com.ralphmarondev.composeworld.core.util

import com.ralphmarondev.composeworld.core.data.local.user.UserEntity
import com.ralphmarondev.composeworld.core.domain.model.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        username = username,
        password = password,
        fullName = fullName,
        passwordHint = passwordHint,
        profilePath = profilePath
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        username = username,
        password = password,
        fullName = fullName,
        passwordHint = passwordHint,
        profilePath = profilePath
    )
}