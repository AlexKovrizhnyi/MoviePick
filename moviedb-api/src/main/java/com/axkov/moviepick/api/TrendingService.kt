package com.axkov.moviepick.api

import com.axkov.moviepick.api.entities.TrendingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


/**
 * TrendingService provides the daily or weekly trending items
 */
interface TrendingService {

    @GET("trending/movie/week")
    fun getTrendingWeek(): Single<TrendingResponse>
}