package com.axkov.moviepick.api

import retrofit2.Retrofit

class TmdbApi(
    private val apiKeyProvider: TmdbApiKeyProvider,
    private val retrofit: Retrofit,
) {
    companion object {
        const val API_HOST = "api.themoviedb.org"
        const val API_VERSION = "3"
        const val API_URL = "https://${API_HOST}/${API_VERSION}/"

        const val PARAM_API_KEY = "api_key"
    }

    val apiKey: String
        get() = apiKeyProvider.requireApiKey()

    val trending: TrendingService by lazy { retrofit.create(TrendingService::class.java) }
//    val popular by lazy { retrofit.create(PopularService::class.java)}

}