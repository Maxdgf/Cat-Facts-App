package com.maxdgf.top15randomcatfacts.di

import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule,
        repositoryModule,
        viewModelModule,
        dataStoreModule
    )
}