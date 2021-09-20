package com.axkov.moviepick.core.network.di

// TODO: DELETE

//import com.axkov.moviepick.core.network.BuildConfig
//import com.axkov.moviepick.core.network.api.MovieDbApiService
//import dagger.Module
//import dagger.Provides
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.CallAdapter
//import retrofit2.Converter
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//abstract class NetworkModule {
//
//    companion object {
//        @Singleton
//        @Provides
//        fun provideGsonConverterFactory(): Converter.Factory =
//            GsonConverterFactory.create()
//
//        @Singleton
//        @Provides
//        fun provideRxJavaCallAdapterFactory(): CallAdapter.Factory =
//            RxJava3CallAdapterFactory.create()
//
//        @Singleton
//        @Provides
//        fun provideOkHttpClient(): OkHttpClient =
//            OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().apply {
//                    level = if (BuildConfig.DEBUG) {
//                        HttpLoggingInterceptor.Level.BODY
//                    } else {
//                        HttpLoggingInterceptor.Level.NONE
//                    }
//                })
//                .build()
//
//        @Singleton
//        @Provides
//        fun provideRetrofit(
//            okHttpClient: OkHttpClient,
//            converterFactory: Converter.Factory,
//            callAdapterFactory: CallAdapter.Factory
//        ): Retrofit =
//            Retrofit.Builder()
//                .baseUrl(MovieDbApiService.BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(converterFactory)
//                .addCallAdapterFactory(callAdapterFactory)
//                .build()
//
//        @Singleton
//        @Provides
//        fun provideMovieDbService(retrofit: Retrofit): MovieDbApiService =
//            retrofit.create(MovieDbApiService::class.java)
//    }
//}