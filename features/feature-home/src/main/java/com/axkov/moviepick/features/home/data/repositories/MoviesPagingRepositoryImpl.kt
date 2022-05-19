package com.axkov.moviepick.features.home.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.observable
import com.axkov.moviepick.api.TmdbApi
import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.core.domain.models.Movie
import com.axkov.moviepick.features.home.data.sources.remote.MoviesPagingSource
import com.axkov.moviepick.features.home.domain.repositories.MoviesPagingRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@FeatureScope
internal class MoviesPagingRepositoryImpl @Inject constructor(
    private val api: TmdbApi
) : MoviesPagingRepository {

    override fun getTrendingMovies(): Observable<PagingData<Movie>> =
        getMovies(TmdbApi.ApiType.TRENDING)

    override fun getPopularMovies(): Observable<PagingData<Movie>> =
        getMovies(TmdbApi.ApiType.POPULAR)

    override fun getTopRatedMovies(): Observable<PagingData<Movie>> =
        getMovies(TmdbApi.ApiType.TOP_RATED)

    override fun getUpcomingMovies(): Observable<PagingData<Movie>> =
        getMovies(TmdbApi.ApiType.UPCOMING)

    private fun getMovies(apiType: TmdbApi.ApiType): Observable<PagingData<Movie>> {
        return Pager(
            config = getPagingConfig(),
            pagingSourceFactory = { MoviesPagingSource(api, apiType) }
        ).observable
    }

    private fun getPagingConfig(
        pageSize: Int = DEFAULT_PAGE_SIZE,
        maxSize: Int = DEFAULT_MAX_SIZE,
        initialLoadSize: Int = DEFAULT_INITIAL_LOAD_SIZE,
        prefetchDistance: Int = DEFAULT_PREFETCH_DISTANCE,
        enablePlaceholders: Boolean = true
    ): PagingConfig =
        PagingConfig(
            pageSize = pageSize,
            maxSize = maxSize,
            initialLoadSize = initialLoadSize,
            prefetchDistance = prefetchDistance,
            enablePlaceholders = enablePlaceholders
        )

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val DEFAULT_MAX_SIZE = 30
        const val DEFAULT_INITIAL_LOAD_SIZE = 30
        const val DEFAULT_PREFETCH_DISTANCE = 5
    }

}