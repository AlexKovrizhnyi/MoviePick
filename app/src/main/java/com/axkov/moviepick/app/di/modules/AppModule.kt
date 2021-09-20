package com.axkov.moviepick.app.di.modules

import com.axkov.moviepick.app.di.annotations.AppScope
import com.axkov.moviepick.app.utils.AndroidResourceProvider
import com.axkov.moviepick.app.utils.ResourceProvider
import com.axkov.moviepick.core.network.api.MovieDbApiService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {
    @Binds
    @AppScope
    abstract fun bindResourceProvider(provider: AndroidResourceProvider): ResourceProvider

    companion object {
        @Provides
        @AppScope
        fun provideMovieDbApiService(): MovieDbApiService = MovieDbApiService()
    }
}