package com.axkov.moviepick.data.repositories

import com.axkov.moviepick.core.data.Data
import com.axkov.moviepick.core.models.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface PopularMoviesRepository {

    fun observeForObservable(count: Int, offset: Int): Observable<Data<List<Movie>>>
//    fun observeForObservable(count: Int, offset: Int): Observable<List<Movie>>

//    fun observeForObservable(count: Int, offset: Int): Observable<List<PopularEntryWithMovie>>

    fun updatePopularMovies(page: Int, resetOnSave: Boolean): Completable
}