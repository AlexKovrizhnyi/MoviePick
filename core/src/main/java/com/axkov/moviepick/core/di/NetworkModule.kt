package com.axkov.moviepick.core.di

import com.axkov.moviepick.api.MovieDbApi
import com.axkov.moviepick.api.MovieDbApiKeyProvider
import com.axkov.moviepick.core.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    companion object {
        @Singleton
        @Provides
        fun provideGsonConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()

        @Singleton
        @Provides
        fun provideRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory =
            RxJava3CallAdapterFactory.create()

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                })
                .build()


        @Singleton
        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory,
            callAdapterFactory: RxJava3CallAdapterFactory
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(MovieDbApi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()

        @Singleton
        @Provides
        fun provideMovieDbApiKeyProvider(): MovieDbApiKeyProvider = object : MovieDbApiKeyProvider {
            override val apiKey: String
                get() = BuildConfig.MOVIE_DB_API_KEY
        }

        @Singleton
        @Provides
        fun provideMovieDbApi(
            apiKeyProvider: MovieDbApiKeyProvider,
            retrofit: Retrofit
        ): MovieDbApi = MovieDbApi(apiKeyProvider, retrofit)
    }
}