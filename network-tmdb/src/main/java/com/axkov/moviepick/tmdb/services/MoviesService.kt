package com.axkov.moviepick.tmdb.services

import com.axkov.moviepick.tmdb.enums.MediaType
import com.axkov.moviepick.tmdb.enums.MovieList
import com.axkov.moviepick.tmdb.enums.TimeWindow
import com.axkov.moviepick.tmdb.models.MovieListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    /**
     * Get a list of the current popular movies on TMDB. This list updates daily.
     */
    @GET("movie/popular")
    fun fetchPopularMovies(
        @Query("page") page: Int
    ): Single<MovieListResponse>

    /**
     * Get the top rated movies on TMDB.
     */
    @GET("movie/top_rated")
    fun fetchTopRatedMovies(
        @Query("page") page: Int
    ): Single<MovieListResponse>

    /**
     * Get a list of upcoming movies in theatres.
     */
    @GET("movie/upcoming")
    fun fetchUpcomingMovies(
        @Query("page") page: Int
    ): Single<MovieListResponse>

    /**
     * Get a list of trending movies
     */
    @GET("trending/{media_type}/{time_window}")
    fun fetchTrendingWeek(
        @Path("media_type") mediaType: MediaType,
        @Path("time_window") timeWindow: TimeWindow,
        @Query("page") page: Int
    ): Single<MovieListResponse>
}