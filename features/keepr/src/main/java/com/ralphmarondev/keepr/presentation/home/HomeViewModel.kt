package com.ralphmarondev.keepr.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.data.repository.KeeprRepositoryImpl
import com.ralphmarondev.keepr.domain.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModelFactory(private val keeprDao: KeeprDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(keeprDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class HomeViewModel(private val keeprDao: KeeprDao) : ViewModel() {
    private val keeprRepository = KeeprRepositoryImpl(keeprDao)

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories:StateFlow<List<Category>> get() = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            try{
                val categories = keeprRepository.getAllCategories()
                _categories.value = categories
                Log.d("HomeViewModel", "Fetching categories...")
            }catch (e: Exception){
                Log.e("HomeViewModel", "Error fetching categories: ${e.message}")
            }
        }
    }
}