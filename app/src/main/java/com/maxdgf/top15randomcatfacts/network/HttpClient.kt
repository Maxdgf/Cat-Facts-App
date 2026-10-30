package com.maxdgf.top15randomcatfacts.network


import android.util.Log

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json

import com.maxdgf.top15randomcatfacts.BuildConfig

private const val KTOR_LOG_TAG = "* [Ktor]"

private const val KTOR_REQUEST_TIMEOUT = 20_000L
private const val KTOR_CONNECT_TIMEOUT = 15_000L
private const val KTOR_SOCKET_TIMEOUT = 10_000L

private const val CAT_FACTS_COUNT = 15

val httpClient = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                useAlternativeNames = true
                ignoreUnknownKeys = true
                encodeDefaults = false
            }
        )
    }

    install(HttpTimeout) {
        requestTimeoutMillis = KTOR_REQUEST_TIMEOUT
        connectTimeoutMillis = KTOR_CONNECT_TIMEOUT
        socketTimeoutMillis = KTOR_SOCKET_TIMEOUT
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v(KTOR_LOG_TAG, message)
            }
        }
        level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
    }

    defaultRequest {
        url("https://meowfacts.herokuapp.com/?count=$CAT_FACTS_COUNT")
    }
}