package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class UpdateAccountUseCase(
    private val repository: KeeprRepository
) {
    suspend fun updateAccount(
        id: Int,
        newName: String,
        newUsername: String,
        newPassword: String
    ): Boolean {
        return try {
            repository.updateAccount(
                id = id,
                name = newName,
                username = newUsername,
                password = newPassword
            )
            Log.d("Keepr", "Successfully updated `$id`")
            true
        } catch (e: Exception) {
            Log.e("Keepr", "Updating account error: ${e.message}")
            false
        }
    }
}