package com.axkov.moviepick.api

interface MovieDbApiKeyProvider {
    val apiKey: String?
}

fun MovieDbApiKeyProvider.requireApiKey(): String = checkNotNull(apiKey)