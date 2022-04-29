package com.axkov.moviepick.data

import com.axkov.moviepick.data.daos.MoviesDao
import com.axkov.moviepick.data.daos.PopularMoviesDao

interface MoviePickDatabase {

    fun popularMoviesDao(): PopularMoviesDao

    fun moviesDao(): MoviesDao
}