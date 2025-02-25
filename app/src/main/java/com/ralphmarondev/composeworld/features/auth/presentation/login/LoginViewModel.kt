package com.ralphmarondev.composeworld.features.auth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.composeworld.core.domain.model.Result
import com.ralphmarondev.composeworld.core.domain.model.User
import com.ralphmarondev.composeworld.core.domain.usecases.CreateUserUseCase
import com.ralphmarondev.composeworld.core.domain.usecases.IsUserExistsUseCase
import com.ralphmarondev.composeworld.core.domain.usecases.IsUserTableEmptyUseCase
import com.ralphmarondev.composeworld.features.auth.preferences.AuthPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val preferences: AuthPreferences,
    private val createUserUseCase: CreateUserUseCase,
    private val isUserTableEmptyUseCase: IsUserTableEmptyUseCase,
    private val isUserExistsUseCase: IsUserExistsUseCase
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _rememberMe = MutableStateFlow(preferences.isRememberMeChecked())
    val rememberMe: StateFlow<Boolean> get() = _rememberMe

    private val _showForgotPasswordDialog = MutableStateFlow(false)
    val showForgotPasswordDialog: StateFlow<Boolean> get() = _showForgotPasswordDialog

    private val _response = MutableStateFlow<Result?>(null)
    val response: StateFlow<Result?> get() = _response

    init {
        if (preferences.isRememberMeChecked()) {
            val savedUsername = preferences.getRememberedUsername()
            val savedPassword = preferences.getRememberedPassword()

            _username.value = savedUsername ?: ""
            _password.value = savedPassword ?: ""
        }
    }

    fun onUsernameChange(value: String) {
        _username.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun toggleRememberMe() {
        _rememberMe.value = !_rememberMe.value
        preferences.toggleRememberMe()
    }

    fun toggleForgotPasswordDialog() {
        _showForgotPasswordDialog.value = !_showForgotPasswordDialog.value
    }

    fun login() {
        val username = _username.value.trim()
        val password = _password.value.trim()

        viewModelScope.launch {
            if (username.isBlank() || password.isBlank()) {
                _response.value = Result(
                    success = false,
                    message = "Username or password cannot be empty!"
                )
                Log.e(
                    "Auth",
                    "Success: ${_response.value?.success}, Message: ${_response.value?.message}"
                )
                return@launch
            }

            if (isUserTableEmptyUseCase()) {
                createUserUseCase(
                    user = User(
                        username = "ralphmaron",
                        password = "iscute"
                    )
                )
            }
            val onLogin = isUserExistsUseCase(username = username, password = password)

            if (onLogin) {
                _response.value = Result(
                    success = true,
                    message = "Login successful!"
                )
                Log.d(
                    "Auth",
                    "Success: ${_response.value?.success}, Message: ${_response.value?.message}"
                )

                if (_rememberMe.value) {
                    preferences.setUsernameToRemember(username)
                    preferences.setPasswordToRemember(password)
                }
            } else {
                _response.value = Result(
                    success = false,
                    message = "Login failed!"
                )
                Log.e(
                    "Auth",
                    "Success: ${_response.value?.success}, Message: ${_response.value?.message}"
                )
            }
        }
    }
}