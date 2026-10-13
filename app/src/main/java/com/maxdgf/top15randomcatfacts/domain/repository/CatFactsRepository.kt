package com.maxdgf.top15randomcatfacts.domain.repository

import com.maxdgf.top15randomcatfacts.domain.model.CatFacts

interface CatFactsRepository {
    suspend fun getRandomCatsFacts(languageCode: String): CatFacts
}