package com.axkov.moviepick.data.sources.remote

import android.accounts.NetworkErrorException
import com.axkov.moviepick.api.TmdbApi
import com.axkov.moviepick.api.models.MovieDto
import com.axkov.moviepick.core.data.Result
import com.axkov.moviepick.core.di.annotations.FeatureScope
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

@FeatureScope
class PopularMoviesDataSource @Inject constructor(
    private val api: TmdbApi
) {

    // TODO: Implement pageSize
    fun getPopularMovies(page: Int, pageSize: Int): Single<Result<List<MovieDto>>> {
        return api.moviesService.fetchPopularMovies(page)
            .map {
                Timber.d("Network request on thread: ${Thread.currentThread().name}")
                Result.Success(it.results) as Result<List<MovieDto>> }
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

//    fun getPopularMovies(page: Int): Single<Result<List<Movie>>> {
//
//        return api.moviesService.fetchPopularMovies(page)
//            .subscribeOn(Schedulers.io())
//            .map {
//                it.results.map { movieDto -> movieDto.toDomain()}
//            }
//            .map {
//                Result.Success(it) as Result<List<Movie>>
//            }
//            .onErrorReturn { error ->
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
//    }
}