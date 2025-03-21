package com.ralphmarondev.settings.presentation.general.backup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.core.data.local.preferences.AppPreferences
import com.ralphmarondev.core.util.Result
import com.ralphmarondev.user_settings.data.local.preferences.UserSettingsPreferences
import com.ralphmarondev.user_settings.data.network.firebase.FirebaseAuthManager
import com.ralphmarondev.user_settings.data.network.firebase.FirebaseDatabaseManager
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.usecases.GetUserDetailByUsername
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BackupAndRestoreViewModel(
    private val appPreferences: AppPreferences,
    private val userSettingsPreferences: UserSettingsPreferences,
    private val getUserDetailByUsername: GetUserDetailByUsername,
    private val firebaseAuthManager: FirebaseAuthManager,
    private val firebaseDatabaseManager: FirebaseDatabaseManager
) : ViewModel() {

    private val _currentUser =
        MutableStateFlow(
            User(id = -1, username = "failed", password = "failed", fullName = "Failed to load")
        )
    val currentUser = _currentUser.asStateFlow()

    private val _response = MutableStateFlow<Result?>(null)
    val response = _response.asStateFlow()

    init {
        viewModelScope.launch {
            val savedUsername = userSettingsPreferences.getSavedUsername()

            if (savedUsername != null) {
                val user = getUserDetailByUsername(savedUsername)
                _currentUser.value = user
            } else {
                Log.e("App", "Error: saved username is empty!")
            }
        }
    }

    /*
     *  - Get username
     *  - Check if username exists on firebase
     *  - If not, prompt to register their username and backup data
     *  - If exists, prompt to confirm backup their data and it will override previous backup.
     */
    fun onBackup() {
        Log.d("App", "Backing up data...")

        viewModelScope.launch {
            val user = _currentUser.value

            if (user.username == "failed" || user.password == "failed") {
                Log.e("App", "User data is invalid. Backup aborted.")
                return@launch
            }

            val signInSuccess =
                firebaseAuthManager.signIn("${user.username}@gmail.com", user.password)
            if (!signInSuccess) {
                Log.e("App", "Sign in failed. Backup aborted.")
                return@launch
            }

            val themeSettings = appPreferences.isDarkTheme()
            val backupData = mapOf("theme" to themeSettings)

            val success = firebaseDatabaseManager.backupUserSettings(user.username, backupData)
            if (success) {
                Log.d("App", "Backup completed successfully.")
            } else {
                Log.e("App", "Backup failed.")
            }
        }
    }

    /*
     *  - Get username
     *  - Check if username exists on firebase
     *  - If not, prompt to register their username and restore data
     *  - If exists, prompt to confirm restore their data and it will override all of their current configuration.
     */
    fun onRestore() {
        Log.d("App", "Restoring data...")
    }
}
