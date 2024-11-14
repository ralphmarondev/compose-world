package com.ralphmarondev.keepr.presentation.subcategories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.data.repository.KeeprRepositoryImpl
import com.ralphmarondev.keepr.domain.model.Subcategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SubCategoriesViewModelFactory(private val keeprDao: KeeprDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubCategoriesViewModel::class.java)) {
            return SubCategoriesViewModel(keeprDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}

class SubCategoriesViewModel(private val keeprDao: KeeprDao) : ViewModel() {
    val keeprRepository = KeeprRepositoryImpl(keeprDao)

    private val _subCategories = MutableStateFlow<List<Subcategory>>(emptyList())
    val subCategories: StateFlow<List<Subcategory>> get() = _subCategories

    init {
        fetchSubcategories()
    }

    private fun fetchSubcategories() {

    }
}