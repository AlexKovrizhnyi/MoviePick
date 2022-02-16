package com.axkov.moviepick.data.sources.local

import com.axkov.moviepick.data.daos.MoviesDao
import com.axkov.moviepick.data.daos.PopularMoviesDao
import com.axkov.moviepick.data.entities.MovieEntity
import com.axkov.moviepick.data.entities.PopularMovieEntry
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class MoviesDataStore @Inject constructor(
    private val moviesDao: MoviesDao

) {

    fun observeForObservable(category: String, count: Int, offset: Int) =
//        moviesDao.entriesObservable(category, count, offset)
        moviesDao.entriesObservable(category)

    fun deleteAll() = moviesDao.deleteAll()

    fun saveMovies(entities: List<MovieEntity>) {
        moviesDao.insertAll(entities)
    }


}