package com.axkov.moviepick.data.repositories

import com.axkov.moviepick.core.data.Data
import com.axkov.moviepick.core.models.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface MoviesRepository {

    fun observeForObservable(count: Int, offset: Int): Observable<Data<List<Movie>>>

    fun updateMovies(page: Int, resetOnSave: Boolean): Completable
}