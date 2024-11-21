package com.ralphmarondev.settings.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.data.user.User
import com.ralphmarondev.data.user.UserDao
import com.ralphmarondev.settings.data.repository.SettingsRepositoryImpl
import com.ralphmarondev.settings.domain.usecases.GetCurrentUserDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModelFactory(
    private val dao: UserDao,
    private val username: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dao = dao, username = username) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class HomeViewModel(
    private val dao: UserDao,
    private val username: String
) : ViewModel() {
    private val user = User(id = -1, fullName = "", username = "", password = "")
    private val _currentUser = MutableStateFlow(user)
    val currentUser: StateFlow<User> get() = _currentUser

    private val repository = SettingsRepositoryImpl(dao)
    private val getCurrentUserDetailsUseCase = GetCurrentUserDetailsUseCase(repository)

    init {
        fetchCurrentUser()
    }

    private fun fetchCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserDetailsUseCase.getCurrentUserDetails(username)
        }
    }
}