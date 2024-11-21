package com.ralphmarondev.keepr.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.ralphmarondev.keepr.domain.model.Account
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.Subcategory
import kotlinx.coroutines.flow.Flow

@Dao
interface KeeprDao {

    @Query("SELECT * FROM Category")
    fun getCategories(): Flow<List<Category>>

    @Query("SELECT * FROM Subcategory WHERE categoryName=:categoryName")
    fun getSubcategories(categoryName: String): Flow<List<Subcategory>>

    @Query("SELECT * FROM Account WHERE subCategoryName=:subCategoryName")
    fun getAccounts(subCategoryName: String): Flow<List<Account>>

    @Upsert
    suspend fun insertCategory(category: Category)

    @Upsert
    suspend fun insertSubCategory(subCategory: Subcategory)

    @Upsert
    suspend fun insertAccount(account: Account)


    // for create defaults
    @Upsert
    suspend fun insertCategories(categories: List<Category>)

    @Upsert
    suspend fun insertSubCategories(subCategories: List<Subcategory>)
}