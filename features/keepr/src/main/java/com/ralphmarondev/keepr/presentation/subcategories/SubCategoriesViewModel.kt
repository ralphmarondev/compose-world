package com.ralphmarondev.keepr.presentation.subcategories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.data.repository.KeeprRepositoryImpl
import com.ralphmarondev.keepr.domain.model.Subcategory
import com.ralphmarondev.keepr.domain.usecases.CreateNewSubCategoryUseCase
import com.ralphmarondev.keepr.domain.usecases.GetSubCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubCategoriesViewModelFactory(
    private val keeprDao: KeeprDao,
    private val categoryName: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubCategoriesViewModel::class.java)) {
            return SubCategoriesViewModel(keeprDao, categoryName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}

class SubCategoriesViewModel(
    private val keeprDao: KeeprDao,
    private val categoryName: String
) : ViewModel() {

    private val keeprRepository = KeeprRepositoryImpl(keeprDao)
    private val getSubCategoriesUseCase = GetSubCategoriesUseCase(keeprRepository)
    private val createNewSubCategoryUseCase = CreateNewSubCategoryUseCase(keeprRepository)

    private val _subCategories = MutableStateFlow<List<Subcategory>>(emptyList())
    val subCategories: StateFlow<List<Subcategory>> get() = _subCategories

    private val _showNewSubCategory = MutableStateFlow(false)
    val showNewSubCategory: StateFlow<Boolean> get() = _showNewSubCategory

    init {
        fetchSubcategories()
    }

    private fun fetchSubcategories() {
        viewModelScope.launch {
            getSubCategoriesUseCase(categoryName).collect { subCategories ->
                _subCategories.value = subCategories
            }
        }
    }

    fun toggleShowNewSubCategory() {
        _showNewSubCategory.value = !_showNewSubCategory.value
    }

    fun createNewSubCategory(name: String) {
        viewModelScope.launch {
            createNewSubCategoryUseCase.createNewSubCategory(
                name = name,
                categoryName = categoryName
            )
        }
    }
}