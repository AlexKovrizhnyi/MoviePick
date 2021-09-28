package com.axkov.moviepick.api

import com.axkov.moviepick.api.entities.TrendingResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

fun TrendingService(): TrendingService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(TrendingService.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    return retrofit.create(TrendingService::class.java)
}