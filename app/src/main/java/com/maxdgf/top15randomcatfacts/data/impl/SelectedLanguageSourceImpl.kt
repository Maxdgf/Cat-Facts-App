package com.maxdgf.top15randomcatfacts.data.impl


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import com.maxdgf.top15randomcatfacts.domain.repository.SelectedLanguageSource


class SelectedLanguageSourceImpl(private val dataStore: DataStore<Preferences>) : SelectedLanguageSource {
    private val _selectedLanguageCode = stringPreferencesKey("selected_language_code")

    override fun getSelectedLanguageCode(): Flow<String> = dataStore.data.map {
        return@map it[_selectedLanguageCode] ?: "eng"
    }

    override suspend fun saveSelectedLanguageCode(code: String) {
        dataStore.edit {
            it[_selectedLanguageCode] = code
        }
    }
}