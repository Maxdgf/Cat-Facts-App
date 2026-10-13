package com.maxdgf.top15randomcatfacts.di


import android.content.Context

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataStoreModule = module {
    single { provideDataStore(androidContext()) }
}

fun provideDataStore(context: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile("top_15_cat_facts_preferences")
    }