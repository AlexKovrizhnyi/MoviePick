package com.axkov.moviepick.data.repositories

import androidx.paging.PagingData
import com.axkov.moviepick.core.models.Movie
import io.reactivex.rxjava3.core.Observable

interface MoviesPagingRepository {

    fun getTrendingMovies(): Observable<PagingData<Movie>>

    fun getPopularMovies(): Observable<PagingData<Movie>>

    fun getTopRatedMovies(): Observable<PagingData<Movie>>

    fun getUpcomingMovies(): Observable<PagingData<Movie>>
}