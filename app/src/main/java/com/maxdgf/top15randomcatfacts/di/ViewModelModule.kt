package com.maxdgf.top15randomcatfacts.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.maxdgf.top15randomcatfacts.presentation.main_screen.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}
