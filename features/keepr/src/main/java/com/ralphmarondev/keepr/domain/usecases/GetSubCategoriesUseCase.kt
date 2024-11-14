package com.ralphmarondev.keepr.domain.usecases

import com.ralphmarondev.keepr.domain.model.Subcategory
import com.ralphmarondev.keepr.domain.repository.KeeprRepository
import kotlinx.coroutines.flow.Flow

class GetSubCategoriesUseCase(
   private val repository: KeeprRepository
) {
    suspend operator fun invoke(categoryName: String): Flow<List<Subcategory>> {
        return repository.getSubcategories(categoryName)
    }
}