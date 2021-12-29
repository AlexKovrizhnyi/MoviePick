package com.axkov.moviepick.core.di

import com.axkov.moviepick.api.TmdbApi
import com.axkov.moviepick.api.TmdbApiKeyProvider
import com.axkov.moviepick.api.interceptors.TmdbInterceptor
import com.axkov.moviepick.core.BuildConfig
import com.axkov.moviepick.core.di.annotations.AppScope
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface NetworkModule {

    companion object {
        @[AppScope Provides]
        fun provideGsonConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()

        @[AppScope Provides]
        fun provideRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory =
            RxJava3CallAdapterFactory.create()

        @[AppScope Provides]
        fun provideOkHttpClient(apiKeyProvider: TmdbApiKeyProvider): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                })
                .addInterceptor(TmdbInterceptor(apiKeyProvider))
                .build()


        @[AppScope Provides]
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory,
            callAdapterFactory: RxJava3CallAdapterFactory
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(TmdbApi.API_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()

        @[Reusable Provides]
        fun provideTmdbApiKeyProvider(): TmdbApiKeyProvider =
            object : TmdbApiKeyProvider(BuildConfig.MOVIE_DB_API_KEY) {}

        @[AppScope Provides]
        fun provideMovieDbApi(
            apiKeyProvider: TmdbApiKeyProvider,
            retrofit: Retrofit
        ): TmdbApi = TmdbApi(apiKeyProvider, retrofit)
    }
}