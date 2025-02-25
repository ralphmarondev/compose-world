package com.ralphmarondev.composeworld.core.di

import com.ralphmarondev.composeworld.core.data.local.database.AppDatabase
import com.ralphmarondev.composeworld.core.data.local.user.UserRepositoryImpl
import com.ralphmarondev.composeworld.core.domain.repositories.UserRepository
import com.ralphmarondev.composeworld.core.domain.usecases.CreateUserUseCase
import com.ralphmarondev.composeworld.core.domain.usecases.IsUserExistsUseCase
import com.ralphmarondev.composeworld.core.domain.usecases.IsUserTableEmptyUseCase
import com.ralphmarondev.composeworld.core.preferences.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {
    singleOf(::AppPreferences)

    // user
    single { AppDatabase.createDatabase(androidContext()) }
    single { get<AppDatabase>().userDao() }
    single<UserRepository> { UserRepositoryImpl(get()) }

    factory { CreateUserUseCase(get()) }
    factory { IsUserTableEmptyUseCase(get()) }
    factory { IsUserExistsUseCase(get()) }
}