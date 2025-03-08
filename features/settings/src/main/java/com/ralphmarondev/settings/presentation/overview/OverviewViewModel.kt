package com.ralphmarondev.settings.presentation.overview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.user_settings.data.local.preferences.UserSettingsPreferences
import com.ralphmarondev.user_settings.domain.model.User
import com.ralphmarondev.user_settings.domain.usecases.GetUserDetailByUsername
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OverviewViewModel(
    private val userSettingsPreferences: UserSettingsPreferences,
    private val getUserDetailByUsername: GetUserDetailByUsername
) : ViewModel() {

    private val _currentUser =
        MutableStateFlow(User(id = -1, username = "", password = "", fullName = ""))
    val currentUser = _currentUser.asStateFlow()

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
}