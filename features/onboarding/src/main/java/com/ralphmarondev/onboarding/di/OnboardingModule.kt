package com.ralphmarondev.onboarding.di

import com.ralphmarondev.onboarding.presentation.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val onboardingModule = module {
    viewModelOf(::OnboardingViewModel)
}