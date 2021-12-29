package com.axkov.moviepick.features.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.mapAsync
import com.axkov.moviepick.core.ui.BaseViewModel
import com.axkov.moviepick.features.home.domain.usecases.GetPopularMovies
import com.axkov.moviepick.features.home.domain.usecases.GetTopRatedMovies
import com.axkov.moviepick.features.home.domain.usecases.GetUpcomingMovies
import com.axkov.moviepick.features.home.ui.models.MovieItem
import com.axkov.moviepick.features.home.ui.models.mappers.toMovieItem
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getUpcomingMovies: GetUpcomingMovies
) : BaseViewModel() {

    private val _popular = MutableLiveData<PagingData<MovieItem>>()
    val popular: LiveData<PagingData<MovieItem>>
        get() = _popular

    private val _topRated = MutableLiveData<PagingData<MovieItem>>()
    val topRated: LiveData<PagingData<MovieItem>>
        get() = _topRated

    private val _upcoming = MutableLiveData<PagingData<MovieItem>>()
    val upcoming: LiveData<PagingData<MovieItem>>
        get() = _upcoming

    init {
        getPopular()
        getTopRated()
        getUpcoming()
    }

    @ExperimentalCoroutinesApi
    fun getPopular() {
        compositeDisposable.add(
            getPopularMovies()
                .map {
                    it.mapAsync { movie -> Single.just(movie.toMovieItem()) }
                }
                .cachedIn(viewModelScope)
                .subscribe {
                    _popular.value = it as PagingData<MovieItem>
                    Timber.d("Popular: new PagingData instance is come")
                }
        )
    }

    @ExperimentalCoroutinesApi
    fun getTopRated() {
        compositeDisposable.add(
            getTopRatedMovies()
                .map {
                    it.mapAsync { movie -> Single.just(movie.toMovieItem()) }
                }
                .cachedIn(viewModelScope)
                .subscribe {
                    _topRated.value = it as PagingData<MovieItem>
                    Timber.d("Top rated: new PagingData instance is come")
                }
        )
    }

    @ExperimentalCoroutinesApi
    fun getUpcoming() {
        compositeDisposable.add(
            getUpcomingMovies()
                .map {
                    it.mapAsync { movie -> Single.just(movie.toMovieItem()) }
                }
                .cachedIn(viewModelScope)
                .subscribe {
                    _upcoming.value = it as PagingData<MovieItem>
                    Timber.d("Upcoming: new PagingData instance is come")
                }
        )
    }
}

//internal sealed class HomeViewState {
//
//    object Loading : HomeViewState()
//
//    data class Success<out T: Any>(val result: List<T>) : HomeViewState()
//
//    data class Failure(val errorMessage: String) : HomeViewState()
//}

//internal sealed class HomeViewState<out T: Any> {
//
//    object Loading : HomeViewState<Nothing>()
//
//    data class Success<out T: Any>(val result: List<T>) : HomeViewState<T>()
//
//    data class Failure(val errorMessage: String) : HomeViewState<Nothing>()
//}


//internal sealed class HomeViewState {
//
//    object Loading : HomeViewState()
//
//    data class Success(val result: List<MovieItem>) : HomeViewState()
//
//    data class Failure(val error: String) : HomeViewState()
//}