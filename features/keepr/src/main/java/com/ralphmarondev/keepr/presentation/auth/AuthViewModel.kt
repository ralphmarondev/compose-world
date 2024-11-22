package com.ralphmarondev.keepr.presentation.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModelFactory(
    private val currentUser: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(currentUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class AuthViewModel(
    private val currentUser: String
) : ViewModel() {
    private val char = "•"
    private val _password = MutableStateFlow("")

    private val _passwordIndicator = MutableStateFlow("")
    val passwordIndicator: StateFlow<String> get() = _passwordIndicator

    /* NOTE:
    *       SELECT COUNT(*) FROM DB WHERE PIN = $PIN AND USERNAME = $USERNAME
     */
    fun onLogin(
        response: (Boolean, String?) -> Unit
    ) {
        if (_password.value.length == 4) {
            _password.value = ""
            _passwordIndicator.value = ""
            response(true, null)
            Log.d("Keepr", "Logging in. Password: ${_password.value}")
        }
    }

    fun onButtonClick(value: String) {
        if (value.uppercase() == "C") {
            if (_password.value.isNotEmpty()) {
                _passwordIndicator.value =
                    _passwordIndicator.value.substring(0, _passwordIndicator.value.length - 1)
                _password.value = _password.value.substring(0, _password.value.length - 1)
            }
            return
        }
        if (_password.value.length < 4) {
            _password.value += value
            _passwordIndicator.value += char
            Log.d("Keepr", "Length: ${_password.value.length}")
            Log.d("Keepr", "Value: ${_password.value}")
        }
    }
}