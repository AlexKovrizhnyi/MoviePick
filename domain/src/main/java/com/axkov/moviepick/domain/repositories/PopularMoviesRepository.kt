package com.axkov.moviepick.domain.repositories

import com.axkov.moviepick.domain.models.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface PopularMoviesRepository {

    fun observeForObservable(count: Int, offset: Int): Observable<List<Movie>>

//    fun observeForObservable(count: Int, offset: Int): Observable<List<PopularEntryWithMovie>>

    fun updatePopularMovies(page: Int): Completable
}