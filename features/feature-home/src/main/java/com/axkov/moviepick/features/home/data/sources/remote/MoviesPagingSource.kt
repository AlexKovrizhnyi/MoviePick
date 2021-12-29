package com.axkov.moviepick.features.home.data.sources.remote

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.axkov.moviepick.api.TmdbApi
import com.axkov.moviepick.api.models.MediaType
import com.axkov.moviepick.api.models.MoviesResponsePage
import com.axkov.moviepick.api.models.TimeWindow
import com.axkov.moviepick.core.utils.mappers.toMovie
import com.axkov.moviepick.domain.models.Movie
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

    private fun MoviesResponsePage.toLoadResultMoviesPage(page: Int): LoadResult<Int, Movie> =
        LoadResult.Page(
            data = this.results.map { it.toMovie() },
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (page == this.totalPages) null else page + 1
        )
}

// Old implementation

//internal class TrendingDataSourceOld @Inject constructor(
//    private val api: TmdbApi
//) {
//
//    fun getTrendingWeek(): Single<List<Movie>> {
//        return api.trending.getTrendingWeek(1)
//            .subscribeOn(Schedulers.io())
//            .map {
//                it.results.map { movieDto -> movieDto.toMovie() }
//            }
//    }
//
//    // TODO: Test method that returns result wrapped on Result object
//    fun getTrendingWeekReturnsResult(): Single<Result<List<Movie>>> {
//        return api.trending.getTrendingWeek(1)
//            .subscribeOn(Schedulers.io())
//            .map {
//                it.results.map { movieDto -> movieDto.toMovie() }
//            }
////            .startWith(RequestState.Loading)
//            .map {
//                Result.Success(it) as Result<List<Movie>>
//            }
////            .doOnError {
////                Timber.d(it, "TrendingDataSource doOnError")
////            }
//            .onErrorReturn {error ->
////                Timber.d(error, "TrendingDataSource onErrorReturn")
//
//                val errorMessage = StringBuilder()
//
//                if (error is HttpException) {
//                    val response = error.response()
//                    val responseCode = response?.code()
//                    errorMessage.append("Http Exception. Response code: $responseCode")
//
//                } else if (error is UnknownHostException) {
//                    errorMessage.append("Server doesn't respond")
//                }
//
//                Result.Failure(NetworkErrorException(errorMessage.toString(), error))
//            }
////            .onErrorReturnItem(Result.Failure(NetworkErrorException("Server doesn't respond.")))
//
//
//    }
//}