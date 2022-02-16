package com.axkov.moviepick.features.home.di

import com.axkov.moviepick.data.repositories.PopularMoviesRepositoryImpl
import com.axkov.moviepick.domain.repositories.PopularMoviesRepository
import com.axkov.moviepick.features.home.data.repositories.MoviesPagingRepositoryImpl
import com.axkov.moviepick.features.home.domain.repositories.MoviesPagingRepository
import dagger.Binds
import dagger.Module

@Module
internal interface HomeModule {

    @Binds
    fun bindMoviePagingRepository(repositoryImpl: MoviesPagingRepositoryImpl): MoviesPagingRepository

    @Binds
    fun bindTrendingRepository(trendingRepository: PopularMoviesRepositoryImpl): PopularMoviesRepository

//    @Binds
//    fun bindTrendingRepository(repositoryImpl: TrendingRepositoryImpl): TrendingRepository

    // TODO: Delete viewModelFactory binding
//    @Binds
//    fun bindViewModelFactory(factory: HomeViewModelFactory): ViewModelProvider.Factory
}