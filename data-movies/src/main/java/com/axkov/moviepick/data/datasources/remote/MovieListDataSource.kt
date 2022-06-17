package com.axkov.moviepick.data.datasources.remote

import android.accounts.NetworkErrorException
import com.axkov.moviepick.tmdb.TmdbApi
import com.axkov.moviepick.tmdb.enums.MovieList
import com.axkov.moviepick.tmdb.models.MovieDto
import com.axkov.moviepick.core.data.Result
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

//@FeatureScope
//class TopRatedMoviesDataSource @Inject constructor(
//    private val api: TmdbApi
//) : MoviesDataSource() {
//
//
//}

class MovieListDataSource @Inject constructor(
    private val api: TmdbApi
) {
    
    fun getMovieList(movieList: MovieList, page: Int): Single<Result<List<MovieDto>>> {
        return api.moviesService.fetchMovieList(movieList, page)
            .map {
                Timber.d("Network request on thread: ${Thread.currentThread().name}")
                Result.Success(it.results) as Result<List<MovieDto>>
            }
            .onErrorReturn { error ->
                val errorMessage = StringBuilder()

                if (error is HttpException) {
                    val response = error.response()
                    val responseCode = response?.code()
                    errorMessage.append("Http Exception. Response code: $responseCode")

                } else if (error is UnknownHostException) {
                    errorMessage.append("Server doesn't respond")
                }

                Result.Failure(NetworkErrorException(errorMessage.toString(), error))
            }
    }
}