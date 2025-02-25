package com.ralphmarondev.composeworld.core.domain.usecases

import android.util.Log
import com.ralphmarondev.composeworld.core.domain.repositories.UserRepository

class IsUserExistsUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return try {
            val result = repository.isUserExists(username = username, password = password)
            Log.d("TAG", "IsUserExistsUseCase result: $result")
            result
        } catch (e: Exception) {
            Log.e("TAG", "IsUserExistsUseCase error: ${e.message}")
            false
        }
    }
}