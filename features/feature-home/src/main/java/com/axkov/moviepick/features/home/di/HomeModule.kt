package com.axkov.moviepick.features.home.di

import com.axkov.moviepick.features.home.data.repositories.MoviesPagingRepositoryImpl
import com.axkov.moviepick.features.home.domain.repositories.MoviePagingRepository
import dagger.Binds
import dagger.Module

@Module
internal interface HomeModule {

    @Binds
    fun bindMoviePagingRepository(repositoryImpl: MoviesPagingRepositoryImpl): MoviePagingRepository

    // TODO: Delete viewModelFactory binding
//    @Binds
//    fun bindViewModelFactory(factory: HomeViewModelFactory): ViewModelProvider.Factory
}