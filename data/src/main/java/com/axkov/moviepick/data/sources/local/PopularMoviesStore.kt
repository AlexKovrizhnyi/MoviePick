package com.axkov.moviepick.data.sources.local

import com.axkov.moviepick.data.daos.PopularMoviesDao
import com.axkov.moviepick.data.entities.PopularMovieEntry
import com.axkov.moviepick.data.utils.DatabaseTransactionRunner
import timber.log.Timber
import javax.inject.Inject

class PopularMoviesStore @Inject constructor(
    private val transactionRunner: DatabaseTransactionRunner,
    private val popularMoviesDao: PopularMoviesDao
) {

    fun observeForObservable(count: Int, offset: Int) =
        popularMoviesDao.entriesObservable(count, offset)

    fun observeForPaging() = popularMoviesDao.entriesPagingSource()

    fun getEntries(count: Int, offset: Int) = popularMoviesDao.getEntriesWithMovie(count, offset)

    fun deleteAll() = popularMoviesDao.deleteAll()

    fun savePopularMoviesPage(page: Int, entries: List<PopularMovieEntry>) = transactionRunner {
        Timber.d("saving thread = ${Thread.currentThread().name}")
        popularMoviesDao.deletePage(page)
        popularMoviesDao.insertAll(entries)
    }

//    fun updatePopularMoviesPage(page: Int, entries: List<PopularMovieEntry>): Completable {
//        return popularMoviesDao.deletePage(page)
//            .andThen(popularMoviesDao.insertAll(entries))
//    }
}