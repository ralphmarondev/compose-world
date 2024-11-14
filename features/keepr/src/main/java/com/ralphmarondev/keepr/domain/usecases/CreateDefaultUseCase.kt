package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.model.Category
import com.ralphmarondev.keepr.domain.model.Subcategory
import com.ralphmarondev.keepr.domain.repository.KeeprRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateDefaultUseCase(private val keeprRepository: KeeprRepository) {
    suspend fun createDefaults() = withContext(Dispatchers.IO) {
        try {
            val defaultCategories = listOf(
                Category(name = "Social"),
                Category(name = "Development"),
                Category(name = "Gaming"),
                Category(name = "Entertainment")
            )
            val defaultSubCategories = listOf(
                Subcategory(
                    name = "Tiktok",
                    categoryName = "Social"
                ),
                Subcategory(
                    name = "Instagram",
                    categoryName = "Social"
                ),
                Subcategory(
                    name = "Facebook",
                    categoryName = "Social"
                ),
                Subcategory(
                    name = "LinkedIn",
                    categoryName = "Social"
                ),
                // gaming
                Subcategory(
                    name = "Clash Of Clans",
                    categoryName = "Gaming"
                ),
                Subcategory(
                    name = "Mobile Legends",
                    categoryName = "Gaming"
                ),
                // development
                Subcategory(
                    name = "GitHub",
                    categoryName = "Development"
                ),
                Subcategory(
                    name = "Android Studio",
                    categoryName = "Development"
                )
            )
            keeprRepository.insertCategories(defaultCategories)
            keeprRepository.insertSubCategories(defaultSubCategories)
            Log.d("CreateDefaultUseCase", "Creating defaults successful.")
        } catch (e: Exception) {
            Log.e("CreateDefaultUseCase", "Error creating defaults. ${e.message}")
        }
    }
}