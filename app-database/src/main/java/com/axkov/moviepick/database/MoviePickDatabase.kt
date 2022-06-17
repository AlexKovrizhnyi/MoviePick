package com.axkov.moviepick.database

import com.axkov.moviepick.database.daos.MoviesDao
import com.axkov.moviepick.database.daos.PopularMoviesDao

interface MoviePickDatabase {

    fun popularMoviesDao(): PopularMoviesDao

    fun moviesDao(): MoviesDao
}