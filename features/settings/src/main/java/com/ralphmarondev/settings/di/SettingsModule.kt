package com.ralphmarondev.settings.di

import com.ralphmarondev.settings.presentation.account.AccountSettingsViewModel
import com.ralphmarondev.settings.presentation.general.backup.BackupAndRestoreViewModel
import com.ralphmarondev.settings.presentation.general.feedback.FeedbackViewModel
import com.ralphmarondev.settings.presentation.overview.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::OverviewViewModel)

    viewModelOf(::AccountSettingsViewModel)
    viewModelOf(::BackupAndRestoreViewModel)
    viewModelOf(::FeedbackViewModel)
}