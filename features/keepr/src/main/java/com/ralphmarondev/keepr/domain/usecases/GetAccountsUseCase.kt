package com.ralphmarondev.keepr.domain.usecases

import com.ralphmarondev.keepr.domain.model.Account
import com.ralphmarondev.keepr.domain.repository.KeeprRepository
import kotlinx.coroutines.flow.Flow

class GetAccountsUseCase(private val repository: KeeprRepository) {
    suspend operator fun invoke(subCategory: String): Flow<List<Account>> {
        return repository.getAccounts(subCategory)
    }
}