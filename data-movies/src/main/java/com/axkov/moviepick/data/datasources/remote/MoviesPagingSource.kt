package com.axkov.moviepick.data.datasources.remote

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.axkov.moviepick.core.models.Movie
import com.axkov.moviepick.data.mappers.toDomain
import com.axkov.moviepick.tmdb.TmdbApi
import com.axkov.moviepick.tmdb.enums.MediaType
import com.axkov.moviepick.tmdb.enums.TimeWindow
import com.axkov.moviepick.tmdb.models.MovieListResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class MoviesPagingSource(
    private val api: TmdbApi,
    private val apiType: TmdbApi.ApiType
) : RxPagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movie>> {
        val page = params.key ?: 1

        val response = when (apiType) {
            TmdbApi.ApiType.TRENDING -> api.moviesService.fetchTrendingWeek(
                MediaType.MOVIE,
                TimeWindow.WEEK,
                page
            )
            TmdbApi.ApiType.POPULAR -> api.moviesService.fetchPopularMovies(page)
            TmdbApi.ApiType.TOP_RATED -> api.moviesService.fetchTopRatedMovies(page)
            TmdbApi.ApiType.UPCOMING -> api.moviesService.fetchUpcomingMovies(page)
        }

        return response
            .subscribeOn(Schedulers.io())
            .map { trendingResponse -> trendingResponse.toLoadResultMoviesPage(page) }
            .onErrorReturn { throwable -> LoadResult.Error(throwable) }
    }

    private fun MovieListResponse.toLoadResultMoviesPage(page: Int): LoadResult<Int, Movie> =
        LoadResult.Page(
            data = this.results.map { it.toDomain() },
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == this.totalPages) null else page + 1
        )
}