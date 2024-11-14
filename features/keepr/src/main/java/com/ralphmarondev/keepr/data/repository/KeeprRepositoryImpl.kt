package com.ralphmarondev.keepr.data.repository

import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.domain.model.Account
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.Subcategory
import com.ralphmarondev.keepr.domain.model.User
import com.ralphmarondev.keepr.domain.repository.KeeprRepository
import kotlinx.coroutines.flow.Flow

class KeeprRepositoryImpl(
    private val keeprDao: KeeprDao
) : KeeprRepository {
    override suspend fun register(user: User) {
        keeprDao.register(user)
    }

    override suspend fun login(username: String, password: String): Boolean {
        return keeprDao.login(
            username = username,
            password = password
        )
    }

    override suspend fun getCategories(): Flow<List<Category>> {
        return keeprDao.getCategories()
    }

    override suspend fun getSubcategories(categoryName: String): Flow<List<Subcategory>> {
        return keeprDao.getSubcategories(categoryName)
    }

    override suspend fun getAccounts(subCategoryName: String): Flow<List<Account>> {
        return keeprDao.getAccounts(subCategoryName)
    }

    override suspend fun insertCategory(category: Category) {
        keeprDao.insertCategory(category)
    }

    override suspend fun insertSubcategory(subcategory: Subcategory) {
        keeprDao.insertSubCategory(subcategory)
    }

    override suspend fun insertAccount(account: Account) {
        keeprDao.insertAccount(account)
    }

    override suspend fun insertCategories(categories: List<Category>) {
        keeprDao.insertCategories(categories)
    }

    override suspend fun insertSubCategories(subCategories: List<Subcategory>) {
        keeprDao.insertSubCategories(subCategories)
    }
}