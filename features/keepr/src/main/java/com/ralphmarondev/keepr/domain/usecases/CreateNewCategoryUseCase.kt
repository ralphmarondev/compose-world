package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class CreateNewCategoryUseCase(private val repository: KeeprRepository) {
    suspend fun createNewCategory(name: String, response: (Boolean, String?) -> Unit) {
        try {
            repository.insertCategory(Category(name = name))
            Log.d("CreateNewCategoryUseCase", "New category created: $name")
            response(true, "Created successfully.")
        } catch (e: Exception) {
            Log.e("CreateNewCategoryUseCase", "Error creating new category: ${e.message}")
            response(false, "Creation failed. Error: ${e.message}")
        }
    }
}