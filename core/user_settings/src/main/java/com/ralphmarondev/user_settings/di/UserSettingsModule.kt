package com.ralphmarondev.user_settings.di

import com.ralphmarondev.user_settings.data.local.database.UserSettingsDatabase
import com.ralphmarondev.user_settings.data.local.preferences.UserSettingsPreferences
import com.ralphmarondev.user_settings.data.repository.UserSettingRepositoryImpl
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository
import com.ralphmarondev.user_settings.domain.usecases.CreateDefaultUserUseCase
import com.ralphmarondev.user_settings.domain.usecases.CreateUserUseCase
import com.ralphmarondev.user_settings.domain.usecases.DeleteUserUseCase
import com.ralphmarondev.user_settings.domain.usecases.GetUserDetailByUsername
import com.ralphmarondev.user_settings.domain.usecases.IsUserExistsUseCase
import com.ralphmarondev.user_settings.domain.usecases.UpdateUserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val userSettingsModule = module {
    singleOf(::UserSettingsPreferences)

    single { UserSettingsDatabase.createDatabase(androidContext()) }
    single { get<UserSettingsDatabase>().userDao }
    single<UserSettingRepository> { UserSettingRepositoryImpl(get()) }

    factoryOf(::CreateDefaultUserUseCase)
    factoryOf(::CreateUserUseCase)
    factoryOf(::UpdateUserUseCase)
    factoryOf(::DeleteUserUseCase)
    factoryOf(::GetUserDetailByUsername)
    factoryOf(::IsUserExistsUseCase)
}