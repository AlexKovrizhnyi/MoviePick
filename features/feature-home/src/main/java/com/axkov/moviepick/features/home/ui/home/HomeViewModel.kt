package com.axkov.moviepick.features.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axkov.moviepick.core.ui.Event
import com.axkov.moviepick.features.home.domain.observers.ObservePopularMovies
import com.axkov.moviepick.features.home.domain.usecases.UpdatePopularMovies
import com.axkov.moviepick.features.home.ui.models.mappers.toMovieItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val updatePopularMovies: UpdatePopularMovies,
    observePopularMovies: ObservePopularMovies,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _uiState = MutableLiveData<HomeViewState>(HomeViewState(isLoading = true))
    val uiState: LiveData<HomeViewState> = _uiState

    // TODO: Side-effects event system testing (toasts, navigation, etc.)
    // Google UI guideline recommends propagating ui-effects using ui-state
    // State object might contain a list of events (with unique ids), which will be consumed by UI
    // and handled accordingly.
    private val _eventSubject = PublishSubject.create<Event<HomeViewEvent>>()
    val uiEvent: Observable<Event<HomeViewEvent>> = _eventSubject.hide()

    init {
        observePopularMovies(ObservePopularMovies.Params(10))

        compositeDisposable.add(
            observePopularMovies.observe()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movies ->
//                    if (movies.isEmpty()) {
//                        // TODO: Create empty view (empty view due to no network connection)
////                        _uiState.value = HomeViewState.loading()
//                    } else {
//                        _uiState.value = HomeViewState(
//                            popularItems = movies.map { it.toMovieItem() },
//                            isLoading = false
//                        )
//                    }

                    _uiState.value = HomeViewState(
                        popularItems = movies.map { it.toMovieItem() },
                        isLoading = false
                    )

                }
        )
    }

    fun handleAction(event: HomeViewAction) {
        when (event) {
            HomeViewAction.Refresh -> refresh()

            is HomeViewAction.OnShowMoreCategoryClick -> _eventSubject.onNext(
                Event(HomeViewEvent.NavigateToCategory(event.category))
            )

            is HomeViewAction.OnMovieItemClick -> TODO("Implement OnMovieItemClick action")
        }
    }

    private fun refresh() {

        compositeDisposable.add(
            updatePopularMovies(UpdatePopularMovies.Params(1))
                .doOnSubscribe { _uiState.value = _uiState.value?.copy(isLoading = true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Timber.d("Refresh completed")
                        // TODO: delete this. [Event system testing]
                        _eventSubject.onNext(Event(HomeViewEvent.ShowToast("Refresh completed")))
                    }, {
                        // TODO: Show error message
                        Timber.d("Refresh didn't completed. Error occurred: ${it.message}")
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}