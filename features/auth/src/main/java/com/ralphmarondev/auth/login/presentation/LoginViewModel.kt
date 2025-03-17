package com.ralphmarondev.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.user_settings.data.local.preferences.UserSettingsPreferences
import com.ralphmarondev.user_settings.domain.usecases.IsUserExistsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userSettingPreferences: UserSettingsPreferences,
    private val isUserExistsUseCase: IsUserExistsUseCase
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _rememberMe = MutableStateFlow(true)
    val rememberMe = _rememberMe.asStateFlow()


    fun onUsernameChange(value: String) {
        _username.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun onRememberMeChange() {
        _rememberMe.value = !_rememberMe.value
    }

    fun onLogin(
        response: (Boolean, String) -> Unit
    ) {
        val username = _username.value.trim()
        val password = _password.value.trim()

        if (username.isEmpty() || password.isEmpty()) {
            response(false, "Please fill in all fields!")
            return
        }

        viewModelScope.launch {
            val result = isUserExistsUseCase(username = username, password = password)

            if (result) {
                userSettingPreferences.setSavedUsername(username)
                response(true, "Login successful.")
            } else {
                response(false, "Login failed.")
            }
        }
    }
}