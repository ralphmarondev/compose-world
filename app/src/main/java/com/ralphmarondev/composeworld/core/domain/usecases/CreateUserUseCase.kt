package com.ralphmarondev.composeworld.core.domain.usecases

import android.util.Log
import com.ralphmarondev.composeworld.core.domain.model.User
import com.ralphmarondev.composeworld.core.domain.repositories.UserRepository

class CreateUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        try {
            repository.createUser(user)
            Log.d("TAG", "User ${user.id} created!")
        } catch (e: Exception) {
            Log.e("TAG", "Create user failed: ${e.message}.")
        }
    }
}