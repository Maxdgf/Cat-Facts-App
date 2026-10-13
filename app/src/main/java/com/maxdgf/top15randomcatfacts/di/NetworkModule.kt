package com.maxdgf.top15randomcatfacts.di

import io.ktor.client.HttpClient
import org.koin.dsl.module
import com.maxdgf.top15randomcatfacts.network.CatFactsApiService
import com.maxdgf.top15randomcatfacts.network.httpClient

val networkModule = module {
    single { provideHttpClient() }
    single { provideCatFactsApiService(get()) }
}

fun provideHttpClient(): HttpClient = httpClient

fun provideCatFactsApiService(httpClient: HttpClient) =
    CatFactsApiService(httpClient)