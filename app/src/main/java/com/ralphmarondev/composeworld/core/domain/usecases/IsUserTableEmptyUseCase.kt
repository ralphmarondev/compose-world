package com.ralphmarondev.composeworld.core.domain.usecases

import android.util.Log
import com.ralphmarondev.composeworld.core.domain.repositories.UserRepository

class IsUserTableEmptyUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Boolean {
        return try {
            val result = repository.isUserTableEmpty()
            Log.d("TAG", "IsUserTableEmpty result: $result")
            result
        } catch (e: Exception) {
            Log.e("TAG", "IsUserTableEmpty error: ${e.message}")
            false
        }
    }
}