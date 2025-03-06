package com.ralphmarondev.auth.di

import com.ralphmarondev.auth.login.presentation.LoginViewModel
import com.ralphmarondev.auth.register.presentation.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegistrationViewModel)
}