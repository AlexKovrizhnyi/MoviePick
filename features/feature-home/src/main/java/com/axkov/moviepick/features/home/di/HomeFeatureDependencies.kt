package com.axkov.moviepick.features.home.di

import android.content.Context
import com.axkov.moviepick.core.di.module_injector.FeatureDependencies
import com.axkov.moviepick.data.repositories.MovieListRepository
import com.axkov.moviepick.data.repositories.MoviesPagingRepository
import com.axkov.moviepick.data.repositories.PopularMoviesRepository

interface HomeFeatureDependencies : FeatureDependencies {

    val context: Context

    val moviesPagingRepository: MoviesPagingRepository
    val popularMoviesRepository: PopularMoviesRepository
//    val movieListRepository: MovieListRepository
}