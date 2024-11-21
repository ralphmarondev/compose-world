package com.ralphmarondev.keepr.domain.repository

import com.ralphmarondev.keepr.domain.model.Account
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.Subcategory
import kotlinx.coroutines.flow.Flow

interface KeeprRepository {

    suspend fun getCategories(): Flow<List<Category>>

    suspend fun getSubcategories(categoryName: String): Flow<List<Subcategory>>

    suspend fun getAccounts(subCategoryName: String): Flow<List<Account>>

    suspend fun insertCategory(category: Category)

    suspend fun insertSubcategory(subcategory: Subcategory)

    suspend fun insertAccount(account: Account)

    // for create defaults
    suspend fun insertCategories(categories: List<Category>)

    suspend fun insertSubCategories(subCategories: List<Subcategory>)
}