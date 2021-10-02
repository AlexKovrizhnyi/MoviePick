package com.axkov.moviepick.api

import retrofit2.Retrofit

class MovieDbApi(
    private val apiKeyProvider: MovieDbApiKeyProvider,
    private val retrofit: Retrofit,
) {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    val apiKey: String
        get() = apiKeyProvider.requireApiKey()

    val trending: TrendingService by lazy { retrofit.create(TrendingService::class.java) }
//    val popular by lazy { retrofit.create(PopularService::class.java)}

}