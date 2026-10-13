package com.maxdgf.top15randomcatfacts.domain.repository

import kotlinx.coroutines.flow.Flow

interface SelectedLanguageSource {
    fun getSelectedLanguageCode(): Flow<String>
    suspend fun saveSelectedLanguageCode(code: String)
}