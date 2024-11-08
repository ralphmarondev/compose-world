package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.model.KeeprUser
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class CreateUserUseCase(private val keeprRepository: KeeprRepository) {
    suspend fun createUser(keeprUser: KeeprUser, response: (Boolean, String?) -> Unit) {
        try {
            keeprRepository.createUser(keeprUser)
            response(true, "Success.")
        } catch (e: Exception) {
            Log.e("KEEPR", "Error: ${e.message}")
            response(false, e.message)
        }
    }
}