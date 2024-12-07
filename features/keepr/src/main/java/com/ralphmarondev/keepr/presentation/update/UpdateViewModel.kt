package com.ralphmarondev.keepr.presentation.update

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UpdateViewModel : ViewModel() {
    private val _selectedItem = MutableStateFlow("")
    val selectedItem: StateFlow<String> get() = _selectedItem

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> get() = _showBottomSheet

    fun onSelectedItemChange(value: String) {
        _selectedItem.value = value
    }

    fun toggleShowBottomSheet() {
        _showBottomSheet.value = !_showBottomSheet.value
    }
}