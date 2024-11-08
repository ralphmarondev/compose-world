package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class LoginUseCase(private val keeprRepository: KeeprRepository) {
    suspend fun login(username: String, password: String): Boolean {
        return try {
            keeprRepository.login(
                username = username,
                password = password
            )
        } catch (e: Exception) {
            Log.e("KEEPR", "Error: ${e.message}")
            false
        }
    }
}