package com.axkov.moviepick.data.di

import com.axkov.moviepick.core.di.module_injector.FeatureDependencies
import com.axkov.moviepick.core.utils.ResourceProvider
import com.axkov.moviepick.database.daos.MoviesDao
import com.axkov.moviepick.database.daos.PopularMoviesDao
import com.axkov.moviepick.database.utils.DatabaseTransactionRunner
import com.axkov.moviepick.database.utils.EntityInserter
import com.axkov.moviepick.tmdb.TmdbApi

interface DataMoviesFeatureDependencies : FeatureDependencies {

    val transactionRunner: DatabaseTransactionRunner
    val entityInserter: EntityInserter
    val moviesDao: MoviesDao
    val popularMoviesDao: PopularMoviesDao

    val resourceProvider: ResourceProvider

    val tmdbApi: TmdbApi
}