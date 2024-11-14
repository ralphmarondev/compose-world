package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.model.User
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class CreateUserUseCase(private val keeprRepository: KeeprRepository) {
    suspend fun createUser(user: User, response: (Boolean, String?) -> Unit) {
        try {
            keeprRepository.register(user)
            response(true, "Success.")
        } catch (e: Exception) {
            Log.e("KEEPR", "Error: ${e.message}")
            response(false, e.message)
        }
    }
}