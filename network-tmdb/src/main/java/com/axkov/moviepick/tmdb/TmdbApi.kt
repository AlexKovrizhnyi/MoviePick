package com.axkov.moviepick.tmdb

import com.axkov.moviepick.tmdb.services.MoviesService
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

    val moviesService: MoviesService by lazy { retrofit.create(MoviesService::class.java) }

    enum class ApiType {
        TRENDING, POPULAR, TOP_RATED, UPCOMING
    }
}