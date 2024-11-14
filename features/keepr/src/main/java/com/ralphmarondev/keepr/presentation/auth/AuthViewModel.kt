package com.ralphmarondev.keepr.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.data.repository.KeeprRepositoryImpl
import com.ralphmarondev.keepr.domain.model.KeeprUser
import com.ralphmarondev.keepr.domain.usecases.CreateDefaultUseCase
import com.ralphmarondev.keepr.domain.usecases.CreateUserUseCase
import com.ralphmarondev.keepr.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch

class AuthViewModelFactory(private val keeprDao: KeeprDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(keeprDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class AuthViewModel(private val keeprDao: KeeprDao) : ViewModel() {
    private val keeprRepository = KeeprRepositoryImpl(keeprDao)
    private val createUserUseCase = CreateUserUseCase(keeprRepository)
    private val loginUseCase = LoginUseCase(keeprRepository)
    private val createDefaultUseCase = CreateDefaultUseCase(keeprRepository)

    fun createUser(keeprUser: KeeprUser, response: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            createUserUseCase.createUser(keeprUser, response)
        }
    }

    fun login(username: String, password: String, response: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = loginUseCase.login(
                username = username,
                password = password
            )
            if (result) {
                createDefaults()
            }
            response(result)
        }
    }

    private suspend fun createDefaults() {
        // create default categories [social, gaming, development, entertainment]
        // create default sub categories [
        //  social: tiktok, facebook, instagram, linkedIn,
        // gaming: clash of clans, mobile legends
        // development: github
        // entertainment: ...
        // ]
        createDefaultUseCase.createDefaults()
    }
}