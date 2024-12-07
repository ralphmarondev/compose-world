package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class DeleteAccountUseCase(
    private val repository: KeeprRepository
) {
    suspend fun deleteAccount(id: Int): Boolean {
        return try {
            repository.deleteAccount(id)
            Log.d("Keepr", "Successfully deleted `$id`")
            true
        } catch (e: Exception) {
            Log.e("Keepr", "Delete account error: ${e.message}")
            false
        }
    }
}