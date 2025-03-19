package com.ralphmarondev.settings.presentation.general.backup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.user_settings.data.network.firebase.FirebaseAuthManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BackupAndRestoreViewModel(
    private val firebaseAuthManager: FirebaseAuthManager
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _result = MutableStateFlow(false)
    val result = _result.asStateFlow()

    private val _showRegisterDialog = MutableStateFlow(false)
    val showRegisterDialog = _showRegisterDialog.asStateFlow()


    fun onEmailChange(value: String) {
        _email.value = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun toggleShowRegisterDialog() {
        _showRegisterDialog.value = !_showRegisterDialog.value
    }

    fun onLogin() {
        viewModelScope.launch {
            _result.value = firebaseAuthManager.signIn(
                email = _email.value,
                password = _password.value
            )
            _email.value = ""
            _password.value = ""
        }
    }

    fun onRegister() {
        viewModelScope.launch {
            _result.value = firebaseAuthManager.signUp(
                email = _email.value,
                password = _password.value
            )
            _email.value = ""
            _password.value = ""
        }
    }
}
