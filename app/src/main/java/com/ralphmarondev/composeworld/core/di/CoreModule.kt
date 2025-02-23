package com.ralphmarondev.composeworld.core.di

import com.ralphmarondev.composeworld.core.preferences.AppPreferences
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreModule = module {
    singleOf(::AppPreferences)
}