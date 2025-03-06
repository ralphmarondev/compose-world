package com.ralphmarondev.user_settings.di

import com.ralphmarondev.user_settings.data.local.database.UserSettingsDatabase
import com.ralphmarondev.user_settings.data.repository.UserSettingRepositoryImpl
import com.ralphmarondev.user_settings.domain.repository.UserSettingRepository
import com.ralphmarondev.user_settings.domain.usecases.CreateDefaultUserUseCase
import com.ralphmarondev.user_settings.domain.usecases.CreateUserUseCase
import com.ralphmarondev.user_settings.domain.usecases.DeleteUserUseCase
import com.ralphmarondev.user_settings.domain.usecases.GetUserDetailById
import com.ralphmarondev.user_settings.domain.usecases.IsUserExistsUseCase
import com.ralphmarondev.user_settings.domain.usecases.UpdateUserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val userSettingsModule = module {
    single { UserSettingsDatabase.createDatabase(androidContext()) }
    single { get<UserSettingsDatabase>().userDao }
    single<UserSettingRepository> { UserSettingRepositoryImpl(get()) }

    factory { CreateDefaultUserUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { UpdateUserUseCase(get()) }
    factory { DeleteUserUseCase(get()) }
    factory { GetUserDetailById(get()) }
    factory { IsUserExistsUseCase(get()) }
}