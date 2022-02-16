package com.axkov.moviepick.features.home.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.axkov.moviepick.domain.models.Movie
import com.axkov.moviepick.features.home.domain.repositories.MoviesPagingRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

internal class CategoryViewModel @Inject constructor(
    private val repo: MoviesPagingRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _category = MutableLiveData<PagingData<Movie>>()
    val category: LiveData<PagingData<Movie>>
        get() = _category

    init {
        getPopular()
    }

    @ExperimentalCoroutinesApi
    fun getPopular() {
        compositeDisposable.add(
            repo.getPopularMovies()
                .cachedIn(viewModelScope)
                .subscribe {
                    _category.value = it as PagingData<Movie>
                    Timber.d("Popular: new PagingData instance is come")
                }
        )
    }
}