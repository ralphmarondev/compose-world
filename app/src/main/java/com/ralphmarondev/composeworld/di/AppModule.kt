package com.ralphmarondev.composeworld.di

import com.ralphmarondev.auth.di.authModule
import com.ralphmarondev.composeworld.home.di.homeModule
import com.ralphmarondev.core.di.coreModule
import com.ralphmarondev.notes.di.notesModule
import com.ralphmarondev.onboarding.di.onboardingModule
import com.ralphmarondev.settings.di.settingsModule
import com.ralphmarondev.user_settings.di.userSettingsModule

val appModule = listOf(
    coreModule,
    userSettingsModule,
    onboardingModule,
    authModule,
    settingsModule,
    homeModule,
    notesModule
)