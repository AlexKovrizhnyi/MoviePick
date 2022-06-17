package com.axkov.moviepick.data.di

import com.axkov.moviepick.core.di.module_injector.FeatureApi
import com.axkov.moviepick.data.repositories.MovieListRepository
import com.axkov.moviepick.data.repositories.MoviesPagingRepository
import com.axkov.moviepick.data.repositories.PopularMoviesRepository

interface DataMoviesFeatureApi: FeatureApi {
    val moviesPagingRepository: MoviesPagingRepository
    val popularMoviesRepository: PopularMoviesRepository
//    val movieListRepository: MovieListRepository
}