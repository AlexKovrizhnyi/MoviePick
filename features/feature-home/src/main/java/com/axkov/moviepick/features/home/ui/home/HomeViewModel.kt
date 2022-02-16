package com.axkov.moviepick.features.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.axkov.moviepick.core.ui.BaseViewModel
import com.axkov.moviepick.data.sources.remote.PopularMoviesDataSource
import com.axkov.moviepick.domain.observers.ObservePopularMovies
import com.axkov.moviepick.domain.usecases.UpdatePopularMovies
import com.axkov.moviepick.features.home.ui.models.MovieItem
import com.axkov.moviepick.features.home.ui.models.mappers.toMovieItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val updatePopularMovies: UpdatePopularMovies,
    observePopularMovies: ObservePopularMovies,
) : BaseViewModel() {

    init {
        observePopularMovies(ObservePopularMovies.Params(10))

        // TODO: update movies only when "refresh" is called
        compositeDisposable.add(
            updatePopularMovies(UpdatePopularMovies.Params(1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                }
        )

        compositeDisposable.add(
            observePopularMovies.observe()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movies ->
                    _popular.value = movies.map { it.toMovieItem()}
                }
        )

    }

    private val _popular = MutableLiveData<List<MovieItem>>()
    val popular: LiveData<List<MovieItem>>
        get() = _popular

    private fun refresh() {

        // TODO: update movies only when "refresh" is called
        compositeDisposable.add(
            updatePopularMovies(UpdatePopularMovies.Params(1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

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