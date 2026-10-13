package com.maxdgf.top15randomcatfacts.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

class CatFactsApiService(private val httpClient: HttpClient) {
    suspend fun getRandomCatFacts(languageCode: String): HttpResponse =
        httpClient.get { parameter("lang", languageCode) }
}