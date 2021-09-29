package com.axkov.moviepick.api

import com.axkov.moviepick.api.entities.TrendingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingService {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    @GET("3/trending/movie/week")
    fun getTrendingWeek(
        @Query("api_key") apiKey: String
    ): Single<TrendingResponse>
}