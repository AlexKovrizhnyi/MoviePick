package com.axkov.moviepick.features.home.domain.repositories

import androidx.paging.PagingData
import com.axkov.moviepick.domain.models.Movie
import io.reactivex.rxjava3.core.Observable

interface MoviePagingRepository {

    fun getTrendingMovies(): Observable<PagingData<Movie>>

    fun getPopularMovies(): Observable<PagingData<Movie>>

    fun getTopRatedMovies(): Observable<PagingData<Movie>>

    fun getUpcomingMovies(): Observable<PagingData<Movie>>
}