package com.axkov.moviepick.data

import com.axkov.moviepick.data.daos.MoviesDao
import com.axkov.moviepick.data.daos.PopularMoviesDao

interface MoviePickDatabase {

    fun popularMoviesDao(): PopularMoviesDao

    // TODO: Temporary implementation
    fun moviesDao(): MoviesDao

}