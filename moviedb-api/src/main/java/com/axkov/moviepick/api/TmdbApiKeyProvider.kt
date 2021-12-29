package com.axkov.moviepick.api

/**
 * Provides The Movie Database API key
 */
abstract class TmdbApiKeyProvider(
    private val apiKey: String?
) {

    /**
     * Returns the Movie Database Api key, if the [apiKey] is null throws an [IllegalStateException]
     */
    fun requireApiKey(): String = checkNotNull(apiKey) { "Api key isn't provided." }
}