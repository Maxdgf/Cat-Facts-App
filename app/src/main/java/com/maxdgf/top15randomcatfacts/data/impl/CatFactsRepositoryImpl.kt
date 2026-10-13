package com.maxdgf.top15randomcatfacts.data.impl

import io.ktor.client.call.body
import com.maxdgf.top15randomcatfacts.domain.model.CatFacts
import com.maxdgf.top15randomcatfacts.domain.repository.CatFactsRepository
import com.maxdgf.top15randomcatfacts.network.CatFactsApiService

class CatFactsRepositoryImpl(private val catFactsApiService: CatFactsApiService) : CatFactsRepository {
    override suspend fun getRandomCatsFacts(languageCode: String): CatFacts =
        catFactsApiService.getRandomCatFacts(languageCode).body<CatFacts>()
}