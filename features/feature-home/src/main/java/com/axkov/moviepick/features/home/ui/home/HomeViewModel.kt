package com.axkov.moviepick.features.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axkov.moviepick.core.data.Data
import com.axkov.moviepick.core.domain.enums.MoviesCategory
import com.axkov.moviepick.core.domain.models.Movie
import com.axkov.moviepick.core.ui.UiMessage
import com.axkov.moviepick.features.home.domain.observers.ObservePopularMovies
import com.axkov.moviepick.features.home.domain.usecases.UpdatePopularMovies
import com.axkov.moviepick.features.home.ui.models.mappers.toMovieItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.BehaviorSubject
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val updatePopularMovies: UpdatePopularMovies,
    observePopularMovies: ObservePopularMovies,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _uiState = MutableLiveData<HomeViewState>()
    val uiState: LiveData<HomeViewState> = _uiState

    private val shownMessages = BehaviorSubject.create<Long>()

    private val stateReducer =
        BiFunction { prevState: HomeViewState, partialChanges: PartState ->

            return@BiFunction when (partialChanges) {

                is PartState.MessageShown -> {
                    val messages =
                        prevState.messages.filterNot { it.id == partialChanges.messageId }
                    prevState.copy(
                        messages = messages
                    )
                }

                is PartState.MoviesError -> {
                    prevState
                        .updateCategory(partialChanges.category, loading = false)
                        .copy(messages = prevState.messages + partialChanges.message)
                }

                is PartState.MoviesLoaded -> {
                    prevState.updateCategory(
                        partialChanges.category,
                        items = partialChanges.content,
                        loading = false
                    )
                }

                is PartState.MoviesLoading -> {
                    prevState.updateCategory(
                        partialChanges.category,
                        loading = true
                    )
                }
            }
        }

    init {
        observePopularMovies(ObservePopularMovies.Params(PAYLOAD))

        val originPopularObservable = observePopularMovies.observe().share()

        val popularObservable = originPopularObservable.map { data ->
            when (data) {
                is Data.Failure -> PartState.MoviesError(MoviesCategory.POPULAR, UiMessage(data.error))
                Data.Loading -> PartState.MoviesLoading(MoviesCategory.POPULAR)
                is Data.Success -> PartState.MoviesLoaded(MoviesCategory.POPULAR, data.content.map { it.toMovieItem() })
            }
        }

        val topRatedObservable =
//            Observable.just(DataS.Success(listOf<Movie>()) as DataS<List<Movie>>)
            Observable.just(Data.Failure(IOException("Test exception")) as Data<List<Movie>>)
//            originPopularObservable
                .map { data ->
                    when (data) {
                        is Data.Failure -> PartState.MoviesError(MoviesCategory.TOP_RATED, UiMessage(data.error))
                        Data.Loading -> PartState.MoviesLoading(MoviesCategory.TOP_RATED)
                        is Data.Success -> PartState.MoviesLoaded(MoviesCategory.TOP_RATED, data.content.map { it.toMovieItem() })
                    }
                }

        val partStateMessageShown = shownMessages.map {
            PartState.MessageShown(it)
        }

        Observable.merge(popularObservable, topRatedObservable, partStateMessageShown)
            .scan(HomeViewState(), stateReducer)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Timber.d("State = $it")
                _uiState.value = it
            }.addTo(compositeDisposable)
    }

    fun refresh(fromUser: Boolean = true) {

        updatePopularMovies(UpdatePopularMovies.Params(UpdatePopularMovies.Page.REFRESH, fromUser))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Refresh completed")
                }, {
                    Timber.d("Refresh didn't completed. Error occurred: ${it.message}\n InitCause: ${it.cause?.message}")
                }).addTo(compositeDisposable)
    }

    fun messageShown(messageId: Long) = shownMessages.onNext(messageId)

    override fun onCleared() {
        compositeDisposable.clear()
    }

    private companion object {
        const val PAYLOAD = 10
    }
}