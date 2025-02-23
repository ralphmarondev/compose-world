package com.ralphmarondev.composeworld.features.auth.di

import com.ralphmarondev.composeworld.features.auth.preferences.AuthPreferences
import com.ralphmarondev.composeworld.features.auth.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::AuthPreferences)

    viewModelOf(::LoginViewModel)
}