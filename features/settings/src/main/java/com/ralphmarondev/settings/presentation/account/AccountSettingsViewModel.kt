package com.ralphmarondev.settings.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ralphmarondev.data.user.UserDao

class AccountSettingsViewModeFactory(
    private val dao: UserDao,
    private val currentUser: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountSettingsViewModel::class.java)) {
            return AccountSettingsViewModel(
                dao = dao,
                currentUser = currentUser
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class AccountSettingsViewModel(
    private val dao: UserDao,
    private val currentUser: String
) : ViewModel() {

}