package com.axkov.moviepick.core.network.api

import com.axkov.moviepick.core.network.api.models.TrendingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {
    @GET("trending/movie/week")
    fun getTrendingWeek(
        @Query("api_key") apiKey: String
    ): Single<TrendingResponse>
}