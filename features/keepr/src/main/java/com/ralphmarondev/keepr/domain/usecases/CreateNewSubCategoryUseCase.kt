package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.model.Subcategory
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class CreateNewSubCategoryUseCase(private val repository: KeeprRepository) {
    suspend fun createNewSubCategory(
        name: String,
        categoryName: String,
        response: (Boolean, String?) -> Unit
    ) {
        try {
            repository.insertSubcategory(Subcategory(name = name, categoryName = categoryName))
            Log.d("CreateNewSubCategoryUseCase", "New subcategory created: $name")
            response(true, "Created successfully.")
        } catch (e: Exception) {
            Log.e("CreateNewSubCategoryUseCase", "Error creating new subcategory: ${e.message}")
            response(false, "Creation failed. Error: ${e.message}")
        }
    }
}