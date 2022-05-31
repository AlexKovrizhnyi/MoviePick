package com.axkov.moviepick.data.sources.local

import com.axkov.moviepick.data.utils.EntityInserter
import com.axkov.moviepick.data.daos.MoviesDao
import com.axkov.moviepick.data.entities.MovieEntity
import io.reactivex.rxjava3.core.Maybe
import timber.log.Timber
import javax.inject.Inject

class MoviesStore @Inject constructor(
    private val entityInserter: EntityInserter,
    private val moviesDao: MoviesDao
) {

    fun saveMovie(movie: MovieEntity): Maybe<Long> = entityInserter.insertOrUpdate(moviesDao, movie)

    fun saveMovies(movies: List<MovieEntity>) = entityInserter.insertOrUpdate(moviesDao, movies)

    fun getIdOrSave(movie: MovieEntity): Maybe<Long> {
        return moviesDao.getIdForTmdbId(movie.tmdbId)
            .flatMap(
                //onSuccessMapper
                { movieId: Long ->
                    Timber.d("getIdOrSave() - onSuccessMapper: movieId = ${movieId}, title = ${movie.title}")
                    Maybe.just(movieId)
                },
                //onErrorMapper
                { error: Throwable ->
                    Timber.d("getIdOrSave() - onErrorMapper: movieId = ${movie.id}, title = ${movie.title}")
                    Maybe.error(error)
                },
                //onCompleteSupplier
                {
                    Timber.d("getIdOrSave() - onCompleteSupplier: movieId = ${movie.id}, title = ${movie.title}")
//                    moviesDao.insert(movie)
//                        .subscribeOn(Schedulers.io())
                    saveMovie(movie)
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