package com.axkov.moviepick.api

interface MovieDbApiKeyProvider {
    /**
     * The Movie Database Api key
     */
    val apiKey: String?

    /**
     * Returns the Movie Database Api key, if the [apiKey] is null throws an [IllegalStateException]
     */
    fun requireApiKey(): String = checkNotNull(apiKey)
}