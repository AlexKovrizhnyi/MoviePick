package com.axkov.moviepick.app.di

import com.axkov.moviepick.api.TrendingService
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
abstract class AppModule {

    companion object {
        @Provides
        @AppScope
        fun provideMovieDbApiService(): TrendingService = TrendingService()
    }
}