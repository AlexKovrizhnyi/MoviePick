package com.axkov.moviepick.database.di

import com.axkov.moviepick.core.di.module_injector.FeatureApi
import com.axkov.moviepick.database.MoviePickDatabase
import com.axkov.moviepick.database.daos.MoviesDao
import com.axkov.moviepick.database.daos.PopularMoviesDao
import com.axkov.moviepick.database.daos.TopRatedMoviesDao
import com.axkov.moviepick.database.daos.UpcomingMoviesDao
import com.axkov.moviepick.database.utils.DatabaseTransactionRunner
import com.axkov.moviepick.database.utils.EntityInserter

interface AppDbApi: FeatureApi {
    val database: MoviePickDatabase

    val entityInserter: EntityInserter
    val transactionRunner: DatabaseTransactionRunner

    val moviesDao: MoviesDao
    val popularMoviesDao: PopularMoviesDao
//    val topRatedMoviesDao: TopRatedMoviesDao
//    val upcomingMoviesDao: UpcomingMoviesDao
}