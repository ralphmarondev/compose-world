package com.ralphmarondev.keepr.domain.repository

import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.KeeprUser
import com.ralphmarondev.keepr.domain.model.SubCategory

interface KeeprRepository {
    suspend fun createUser(keeprUser: KeeprUser)

    suspend fun login(username: String, password: String): Boolean

    suspend fun createDefaultCategory(categories: List<Category>)

    suspend fun getCategoryCount(): Int

    suspend fun createDefaultSubCategory(subCategories: List<SubCategory>)

    suspend fun getSubCategoryCount(): Int


    suspend fun getAllCategories(): List<Category>
}