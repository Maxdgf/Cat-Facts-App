package com.maxdgf.top15randomcatfacts.di


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

import org.koin.dsl.module

import com.maxdgf.top15randomcatfacts.data.impl.CatFactsRepositoryImpl
import com.maxdgf.top15randomcatfacts.data.impl.SelectedLanguageSourceImpl
import com.maxdgf.top15randomcatfacts.domain.repository.CatFactsRepository
import com.maxdgf.top15randomcatfacts.domain.repository.SelectedLanguageSource
import com.maxdgf.top15randomcatfacts.network.CatFactsApiService


val repositoryModule = module {
    single { provideCatFactsRepository(get()) }
    single { provideSelectedLanguageSource(get()) }
}

fun provideCatFactsRepository(catFactsApiService: CatFactsApiService): CatFactsRepository =
    CatFactsRepositoryImpl(catFactsApiService)

fun provideSelectedLanguageSource(dataStore: DataStore<Preferences>): SelectedLanguageSource =
    SelectedLanguageSourceImpl(dataStore)
