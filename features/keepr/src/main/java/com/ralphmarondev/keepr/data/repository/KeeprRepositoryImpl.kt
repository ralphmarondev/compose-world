package com.ralphmarondev.keepr.data.repository

import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.KeeprUser
import com.ralphmarondev.keepr.domain.model.SubCategory
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class KeeprRepositoryImpl(
    private val keeprDao: KeeprDao
) : KeeprRepository {
    override suspend fun createUser(keeprUser: KeeprUser) {
        keeprDao.createUser(keeprUser)
    }

    override suspend fun login(username: String, password: String): Boolean {
        return keeprDao.login(
            username = username,
            password = password
        )
    }

    override suspend fun createDefaultCategory(categories: List<Category>) {
        keeprDao.insertCategories(categories)
    }

    override suspend fun getCategoryCount(): Int {
        return keeprDao.getCategoryCount()
    }

    override suspend fun createDefaultSubCategory(subCategories: List<SubCategory>) {
        keeprDao.insertSubCategory(subCategories)
    }

    override suspend fun getSubCategoryCount(): Int {
        return keeprDao.getSubCategoryCount()
    }


    override suspend fun getAllCategories(): List<Category> {
        return keeprDao.getAllCategories()
    }
}