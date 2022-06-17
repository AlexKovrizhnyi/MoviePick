package com.axkov.moviepick.database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.axkov.moviepick.database.entities.PopularMovieEntry
import com.axkov.moviepick.database.entities.resultentities.PopularEntryWithMovie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class PopularMoviesDao : PaginatedEntryDao<PopularMovieEntry>() {

    @Transaction
    @Query("SELECT * FROM popular_movies WHERE page = :page ORDER BY page_order")
    abstract fun entriesObservable(page: Int): Observable<List<PopularMovieEntry>>

    @Transaction
    @Query("SELECT * FROM popular_movies ORDER BY page, page_order LIMIT :count OFFSET :offset")
    abstract fun entriesObservable(count: Int, offset: Int): Observable<List<PopularEntryWithMovie>>

    @Transaction
    @Query("SELECT * FROM popular_movies ORDER BY page, page_order")
    abstract fun entriesPagingSource(): PagingSource<Int, PopularEntryWithMovie>

    @Transaction
    @Query("SELECT * FROM popular_movies ORDER BY page, page_order LIMIT :count OFFSET :offset")
    abstract fun getEntriesWithMovie(count: Int, offset: Int): Single<List<PopularEntryWithMovie>>

    @Query("DELETE FROM popular_movies WHERE page = :page")
    abstract override fun deletePage(page: Int): Completable

    @Query("DELETE FROM popular_movies")
    abstract override fun deleteAll(): Completable

    @Query("SELECT MAX(page) from popular_movies")
    abstract override fun getLastPage(): Maybe<Int>

}