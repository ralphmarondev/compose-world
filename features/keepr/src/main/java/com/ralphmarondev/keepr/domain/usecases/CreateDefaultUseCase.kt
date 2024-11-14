package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.SubCategory
import com.ralphmarondev.keepr.domain.repository.KeeprRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateDefaultUseCase(private val keeprRepository: KeeprRepository) {
    suspend fun createDefaults() = withContext(Dispatchers.IO) {
        try {
            createDefaultCategory()
            createDefaultSubCategory()
            Log.d("CreateDefaultUseCase", "Creating defaults successful.")
        } catch (e: Exception) {
            Log.e("CreateDefaultUseCase", "Error creating defaults. ${e.message}")
        }
    }

    private suspend fun createDefaultCategory() {
        val existingCount = keeprRepository.getCategoryCount()

        if (existingCount == 0) {
            val categories = listOf(
                Category(name = "Social"), // 0
                Category(name = "Gaming"), // 1
                Category(name = "Development"), // 2
                Category(name = "Entertainment") // 3
            )
            keeprRepository.createDefaultCategory(categories)
            Log.d("CreateDefaultUseCase", "Creating default categories successful.")
        } else {
            Log.d("CreateDefaultUseCase", "Default categories already exist.")
        }
    }

    private suspend fun createDefaultSubCategory() {
        val existingCount = keeprRepository.getSubCategoryCount()

        if (existingCount == 0) {
            val subCategories = listOf(
                // social
                SubCategory(
                    name = "TikTok",
                    category = "Social"
                ),
                SubCategory(
                    name = "Facebook",
                    category = "Social"
                ),
                SubCategory(
                    name = "Instagram",
                    category = "Social"
                ),
                SubCategory(
                    name = "LinkedIn",
                    category = "Social"
                ),

                // gaming
                SubCategory(
                    name = "Clash Of Clans",
                    category = "Gaming"
                ),
                SubCategory(
                    name = "Mobile Legends",
                    category = "Gaming"
                ),

                // development
                SubCategory(
                    name = "GitHub",
                    category = "Development"
                )
            )
            keeprRepository.createDefaultSubCategory(subCategories)
            Log.d("CreateDefaultUseCase", "Creating default subcategories successful.")
        } else {
            Log.e("CreateDefaultUseCase", "Default subcategories already exist.")
        }
    }
}