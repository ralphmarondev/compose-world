package com.ralphmarondev.composeworld.home.di

import com.ralphmarondev.composeworld.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}