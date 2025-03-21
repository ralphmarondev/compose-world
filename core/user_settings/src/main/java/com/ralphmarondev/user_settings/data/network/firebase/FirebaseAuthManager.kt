package com.ralphmarondev.user_settings.data.network.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

/*
 * Note: Testing credentials
 *  - email: edaralphmaron@gmail.com
 *  - password: iscute1234
 */

class FirebaseAuthManager(
    private val firebaseAuth: FirebaseAuth
) {

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    suspend fun signUp(
        email: String,
        password: String,
        displayName: String? = null
    ): Boolean/*Result<FirebaseUser?>*/ {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.let { user ->
                displayName?.let {
                    val profileUpdate = UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()
                    user.updateProfile(profileUpdate).await()
                }
            }
//            Result.success(authResult.user)
            Log.d("App", "Authresult signup: ${authResult.user}")
            authResult.user != null
        } catch (e: Exception) {
//            Result.failure(e)
            Log.e("App", "Sign up error: ${e.message}")
            false
        }
    }

    suspend fun signIn(
        email: String,
        password: String
    ): Boolean/* Result<FirebaseUser?>*/ {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
//            Result.success(authResult.user)
            Log.d("App", "Authresult signin: ${authResult.user}")
            authResult.user != null
        } catch (e: Exception) {
//            Result.failure(e)
            Log.e("App", "Sign in error: $e")
            false
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    suspend fun sendPasswordReset(email: String): Result<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteUser(): Result<Unit> {
        return try {
            firebaseAuth.currentUser?.delete()?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}