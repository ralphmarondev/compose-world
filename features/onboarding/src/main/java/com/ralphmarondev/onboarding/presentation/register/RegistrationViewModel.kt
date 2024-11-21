package com.ralphmarondev.onboarding.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.data.user.User
import com.ralphmarondev.data.user.UserDao
import com.ralphmarondev.onboarding.data.repository.OnboardingRepositoryImpl
import com.ralphmarondev.onboarding.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.launch

class RegistrationViewModelFactory(private val dao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class RegistrationViewModel(private val dao: UserDao) : ViewModel() {
    private val repository = OnboardingRepositoryImpl(dao)
    private val registerUserUseCase = RegisterUserUseCase(repository)

    fun register(
        fullName: String,
        username: String,
        password: String,
        response: (Boolean, String?) -> Unit
    ) {
        val user = User(
            fullName = fullName,
            username = username,
            password = password
        )

        viewModelScope.launch {
            registerUserUseCase.registerUser(user, response)
        }
    }
}