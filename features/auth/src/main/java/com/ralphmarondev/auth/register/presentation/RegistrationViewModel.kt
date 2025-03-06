package com.ralphmarondev.auth.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.usecases.CreateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _fullName = MutableStateFlow("")
    val fullName = _fullName.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _passwordHint = MutableStateFlow("")
    val passwordHint = _passwordHint.asStateFlow()

    private val _response = MutableStateFlow(false)
    val response = _response.asStateFlow()

    private val _selectedScreen = MutableStateFlow(0)
    val selectedScreen = _selectedScreen.asStateFlow()

    private val _showConfirmDialog = MutableStateFlow(false)
    val showConfirmDialog = _showConfirmDialog.asStateFlow()


    fun onFullNameChange(value: String) {
        _fullName.value = value
    }

    fun onUsernameChange(value: String) {
        _username.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun onPasswordHintChange(value: String) {
        _passwordHint.value = value
    }

    fun setSelectedScreen(value: Int) {
        _selectedScreen.value = value
    }

    fun toggleShowConfirmDialog() {
        _showConfirmDialog.value = !_showConfirmDialog.value
    }

    private fun isInputValid(): Boolean {
        val fullName = _fullName.value.trim()
        val username = _username.value.trim()
        val password = _password.value.trim()
        val passwordHint = _passwordHint.value.trim()

        return !(fullName.isEmpty() || username.isEmpty() || password.isEmpty() || passwordHint.isEmpty())
    }

    fun onNext(
        response: (Boolean, String) -> Unit
    ) {
        if (isInputValid()) {
            response(true, "")
        } else {
            response(false, "Please fill in all fields.")
        }
    }

    fun onRegister() {
        viewModelScope.launch {
            val user = User(
                fullName = _fullName.value,
                username = _username.value,
                password = _password.value,
                hintPassword = _passwordHint.value
            )
            createUserUseCase(user)
        }
    }
}