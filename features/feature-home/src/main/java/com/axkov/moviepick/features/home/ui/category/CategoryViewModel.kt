package com.axkov.moviepick.features.home.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.axkov.moviepick.core.enums.MoviesCategory
import com.axkov.moviepick.core.models.Movie
import com.axkov.moviepick.data.repositories.MoviesPagingRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
internal class CategoryViewModel @AssistedInject constructor(
    @Assisted category: MoviesCategory,
    private val repo: com.axkov.moviepick.data.repositories.MoviesPagingRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movies = MutableLiveData<PagingData<Movie>>()
    val movies: LiveData<PagingData<Movie>>
        get() = _movies

    init {
        getData(category)
    }

    @ExperimentalCoroutinesApi
    private fun getData(category: MoviesCategory) {
        val observableMovies = when (category) {
            MoviesCategory.POPULAR -> repo.getPopularMovies()
            MoviesCategory.TOP_RATED -> repo.getTopRatedMovies()
            MoviesCategory.UPCOMING -> repo.getUpcomingMovies()
        }

        compositeDisposable.add(
            observableMovies
                .cachedIn(viewModelScope)
                .subscribe {
                    _movies.value = it
                    Timber.d("new PagingData instance is come")
                }
        )
    }

    @AssistedFactory
    interface Factory {
        fun create(category: MoviesCategory): CategoryViewModel
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}