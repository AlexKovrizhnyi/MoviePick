package com.axkov.moviepick.features.home.di

import com.axkov.moviepick.features.home.data.repositories.TrendingRepositoryImpl
import com.axkov.moviepick.features.home.domain.repositories.TrendingRepository
import dagger.Binds
import dagger.Module

@Module
internal interface HomeModule {

    @Binds
    fun bindTrendingRepository(trendingRepository: TrendingRepositoryImpl): TrendingRepository

//    @Binds
//    fun bindViewModelFactory(factory: HomeViewModelFactory): ViewModelProvider.Factory
}