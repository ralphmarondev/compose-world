package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.model.Account
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class CreateNewAccountUseCase(private val repository: KeeprRepository) {
    suspend fun createAccount(account: Account) {
        try {
            repository.insertAccount(account)
            Log.d("CreateNewAccountUseCase", "Account created successfully.")
        } catch (e: Exception) {
            Log.e("CreateNewAccountUseCase", "Error creating account: ${e.message}")
        }
    }
}