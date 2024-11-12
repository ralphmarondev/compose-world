package com.ralphmarondev.keepr.domain.usecases

import android.util.Log
import com.ralphmarondev.keepr.domain.repository.KeeprRepository

class CreateDefaultUseCase(private val keeprRepository: KeeprRepository) {
    fun createDefaults() {
        try {
            createDefaultCategory()
            createDefaultSubCategory()
            createDefaultPlatformsForSocial()
            createDefaultPlatformsForGaming()
            createDefaultPlatformsForDevelopment()
            Log.d("CreateDefaultUseCase", "Creating defaults successful.")
        } catch (e: Exception) {
            Log.e("CreateDefaultUseCase", "Error creating defaults. ${e.message}")
        }
    }

    private fun createDefaultCategory() {

    }

    private fun createDefaultSubCategory() {

    }

    private fun createDefaultPlatformsForSocial() {

    }

    private fun createDefaultPlatformsForGaming() {

    }

    private fun createDefaultPlatformsForDevelopment() {

    }
}