package com.axkov.moviepick.data.sources.local

import com.axkov.moviepick.data.daos.PopularMoviesDao
import com.axkov.moviepick.data.entities.PopularMovieEntry
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class PopularMoviesStore @Inject constructor(
    private val popularMoviesDao: PopularMoviesDao
) {

    fun observeForObservable(count: Int, offset: Int) =
        popularMoviesDao.entriesObservable(count, offset)

    fun observeForPaging() = popularMoviesDao.entriesPagingSource()

    fun deleteAll() = popularMoviesDao.deleteAll()

    fun updatePopularMoviesPage(page: Int, entries: List<PopularMovieEntry>): Completable {
        return popularMoviesDao.deletePage(page)
            .andThen(popularMoviesDao.insertAll(entries))
//        return popularMoviesDao.insertAll(entries)
    }
}