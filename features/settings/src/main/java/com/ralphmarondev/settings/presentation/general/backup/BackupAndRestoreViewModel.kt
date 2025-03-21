package com.ralphmarondev.settings.presentation.general.backup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ralphmarondev.user_settings.data.network.firebase.FirebaseAuthManager

class BackupAndRestoreViewModel(
    private val firebaseAuthManager: FirebaseAuthManager
) : ViewModel() {

    /*
     *  - Get username
     *  - Check if username exists on firebase
     *  - If not, prompt to register their username and backup data
     *  - If exists, prompt to confirm backup their data and it will override previous backup.
     */
    fun onBackup() {
        Log.d("App", "Backing up data...")
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
