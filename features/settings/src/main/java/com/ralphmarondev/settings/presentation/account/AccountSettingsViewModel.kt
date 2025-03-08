package com.ralphmarondev.settings.presentation.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.user_settings.data.local.preferences.UserSettingsPreferences
import com.ralphmarondev.user_settings.domain.usecases.GetUserDetailByUsername
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccountSettingsViewModel(
    private val userSettingsPreferences: UserSettingsPreferences,
    private val getUserDetailByUsername: GetUserDetailByUsername
) : ViewModel() {

    private val _fullName = MutableStateFlow("")
    val fullName: StateFlow<String> get() = _fullName

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _id = MutableStateFlow(-1)

    private val _showNewFullNameDialog = MutableStateFlow(false)
    val showNewFullNameDialog: StateFlow<Boolean> get() = _showNewFullNameDialog

    private val _showNewUsernameDialog = MutableStateFlow(false)
    val showNewUsernameDialog: StateFlow<Boolean> get() = _showNewUsernameDialog

    private val _showNewPasswordDialog = MutableStateFlow(false)
    val showNewPasswordDialog: StateFlow<Boolean> get() = _showNewPasswordDialog

    private val _selectedImage = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            val savedUsername = userSettingsPreferences.getSavedUsername()
            if (savedUsername != null) {
                val user = getUserDetailByUsername(savedUsername)
                _id.value = user.id
                _fullName.value = user.fullName
                _username.value = user.username
                _password.value = user.password
            } else {
                Log.e("App", "No username found")
            }
        }
    }

    fun toggleShowNewFullNameDialog() {
        _showNewFullNameDialog.value = !_showNewFullNameDialog.value
    }

    fun toggleShowNewUsernameDialog() {
        _showNewUsernameDialog.value = !_showNewUsernameDialog.value
    }

    fun toggleShowNewPasswordDialog() {
        _showNewPasswordDialog.value = !_showNewPasswordDialog.value
    }

    fun saveNewFullName(newName: String, result: (Boolean, String) -> Unit) {
        Log.d("Settings", "New full name: $newName")
        viewModelScope.launch {
            try {
//                dao.updateFullNameById(
//                    newFullName = newName,
//                    id = _id.value
//                )
                result(true, "Full name updated successfully!")
                _fullName.value = newName
            } catch (e: Exception) {
                result(false, "Error: ${e.message}")
            }
        }
    }

    fun saveNewUsername(newUsername: String, result: (Boolean, String) -> Unit) {
        Log.d("Settings", "New username: $newUsername")
        viewModelScope.launch {
            try {
//                dao.updateUserNameById(
//                    newUsername = newUsername,
//                    id = _id.value
//                )
                result(true, "Username updated successfully!")
                _username.value = newUsername
            } catch (e: Exception) {
                result(false, "Error: ${e.message}")
            }
        }
    }

    fun saveNewPassword(newPassword: String, result: (Boolean, String) -> Unit) {
        Log.d("Settings", "New password: $newPassword")
        viewModelScope.launch {
            try {
//                dao.updatePasswordById(
//                    newPassword = newPassword,
//                    id = _id.value
//                )
                result(true, "Password updated successfully!")
                _password.value = newPassword
            } catch (e: Exception) {
                result(false, "Error: ${e.message}")
            }
        }
    }
}