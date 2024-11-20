package com.ralphmarondev.onboarding.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _selectedScreen = MutableStateFlow(0)
    val selectedScreen: StateFlow<Int> get() = _selectedScreen

    fun incrementSelectedScreen() {
        if (_selectedScreen.value < 2) {
            _selectedScreen.value++
        }
    }

    fun decrementSelectedScreen() {
        if (selectedScreen.value > 0) {
            _selectedScreen.value--
        }
    }
}