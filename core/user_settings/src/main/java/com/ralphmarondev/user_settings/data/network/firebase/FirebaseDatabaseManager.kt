package com.ralphmarondev.user_settings.data.network.firebase

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseDatabaseManager(
    private val firebaseDatabase: FirebaseDatabase
) {
    private val database: DatabaseReference = firebaseDatabase.reference

    suspend fun backupUserSettings(
        username: String,
        settings: Map<String, Any>
    ): Boolean {
        return try {
            database.child("users").child(username).setValue(settings).await()
            Log.d("App", "Successfully backed up settings for $username")
            true
        } catch (e: Exception) {
            Log.e("App", "Error backing up settings: $e")
            false
        }
    }

    suspend fun restoreUserSettings(
        username: String
    ): Map<String, Any>? {
        return try {
            val snapshot = database.child("users").child(username).get().await()
            snapshot.value as? Map<String, Any>
        } catch (e: Exception) {
            Log.e("App", "Error restoring settings: $e")
            null
        }
    }
}