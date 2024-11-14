package com.ralphmarondev.keepr.domain.usecases

import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.repository.KeeprRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val repository: KeeprRepository
) {
    suspend operator fun invoke(): Flow<List<Category>> {
        return repository.getCategories()
    }
}