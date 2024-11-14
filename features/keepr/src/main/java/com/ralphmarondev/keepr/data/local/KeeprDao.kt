package com.ralphmarondev.keepr.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.KeeprUser
import com.ralphmarondev.keepr.domain.model.SubCategory

// TODO: Create initial categories and sub categories on first launch
@Dao
interface KeeprDao {

    @Upsert
    suspend fun createUser(keeprUser: KeeprUser)

    @Query("SELECT COUNT(*) > 0 FROM KeeprUser WHERE username=:username AND password=:password and isDeleted=0")
    suspend fun login(username: String, password: String): Boolean

    @Upsert
    suspend fun insertCategories(categories: List<Category>)

    @Query("SELECT COUNT(*) FROM Category")
    suspend fun getCategoryCount(): Int

    @Upsert
    suspend fun insertSubCategory(subCategory: List<SubCategory>)

    @Query("SELECT COUNT(*) FROM SubCategory")
    suspend fun getSubCategoryCount(): Int


    @Query("SELECT * FROM Category")
    suspend fun getAllCategories(): List<Category>
}