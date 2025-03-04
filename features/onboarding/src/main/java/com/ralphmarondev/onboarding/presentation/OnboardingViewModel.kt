package com.ralphmarondev.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.user_settings.domain.usecases.CreateDefaultUserUseCase
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val createDefaultUserUseCase: CreateDefaultUserUseCase
) : ViewModel() {

    fun createDefaultUser() {
        viewModelScope.launch {
            createDefaultUserUseCase()
        }
    }
}