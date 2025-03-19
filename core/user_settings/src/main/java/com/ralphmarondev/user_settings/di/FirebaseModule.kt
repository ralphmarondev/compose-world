package com.ralphmarondev.user_settings.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.ralphmarondev.user_settings.data.network.firebase.FirebaseAuthManager
import org.koin.dsl.module

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseDatabase.getInstance() }

    single { FirebaseAuthManager(get()) }
}