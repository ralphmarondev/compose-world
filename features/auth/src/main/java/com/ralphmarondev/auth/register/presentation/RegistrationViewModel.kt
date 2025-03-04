package com.ralphmarondev.auth.register.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegistrationViewModel : ViewModel() {

    private val _fullName = MutableStateFlow("")
    val fullName = _fullName.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _passwordHint = MutableStateFlow("")
    val passwordHint = _passwordHint.asStateFlow()


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

    fun onRegister() {
        Log.d(
            "App",
            "Full Name: ${_fullName.value}, username: ${_username.value}, password: ${_password.value}, password hint: ${_passwordHint.value}"
        )
    }
}