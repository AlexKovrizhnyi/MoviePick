package com.axkov.moviepick.data.sources.local

import com.axkov.moviepick.data.daos.MoviesDao
import com.axkov.moviepick.data.entities.MovieEntity
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MoviesStore @Inject constructor(
    private val moviesDao: MoviesDao
) {

    fun getIdOrSave(movie: MovieEntity): Maybe<Long> {
        return moviesDao.getIdForTmdbId(movie.tmdbId)
            .flatMap(
                //onSuccessMapper
                { movieId: Long ->
                    Maybe.just(movieId)
                },
                //onErrorMapper
                { error: Throwable ->
                   Maybe.error(error)

                },
                //onCompleteSupplier
                {
                    moviesDao.insert(movie)
                        .subscribeOn(Schedulers.io())
                }
            )
    }

//    fun observeForObservable(category: String, count: Int, offset: Int) =
////        moviesDao.entriesObservable(category, count, offset)
//        moviesDao.entriesObservable(category)
//
//    fun deleteAll() = moviesDao.deleteAll()
//
//    fun saveMovies(entities: List<MovieEntity>) {
//        moviesDao.insertAll(entities)
//    }


}