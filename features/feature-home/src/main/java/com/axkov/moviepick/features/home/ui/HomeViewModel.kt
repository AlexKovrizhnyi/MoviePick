package com.axkov.moviepick.features.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.axkov.moviepick.core.ui.BaseViewModel
import com.axkov.moviepick.core.utils.ResourceProvider
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.domain.usecases.GetTrending
import com.axkov.moviepick.features.home.ui.models.ItemPlaceholder
import com.axkov.moviepick.features.home.ui.models.ListItem
import com.axkov.moviepick.features.home.ui.models.MovieCategoryItem
import com.axkov.moviepick.features.home.ui.models.MovieItem
import com.axkov.moviepick.features.home.ui.models.mappers.toMovieItem
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val resources: ResourceProvider,
    private val getTrending: GetTrending
) : BaseViewModel() {
    private val moviesByCategories = MutableLiveData<List<ListItem>>()

    fun getLoaders(): List<ListItem> {
        return listOf(
            MovieCategoryItem(
                resources.getString(R.string.popular_movies),
                movieList = IntRange(1, 3).map { ItemPlaceholder }
            ),
            MovieCategoryItem(
                resources.getString(R.string.latest_movies),
                movieList = IntRange(1, 3).map { ItemPlaceholder }
            ),
            MovieCategoryItem(
                resources.getString(R.string.upcoming_movies),
                movieList = IntRange(1, 3).map { ItemPlaceholder }
            )
        )
    }

    fun getMovies(): LiveData<List<ListItem>> {
        loadContent()
        return moviesByCategories
    }

    private fun loadContent() {
        compositeDisposable.add(
            getTrending()
                .subscribe(
                    {
                        val movies = listOf(
                            MovieCategoryItem(
                                resources.getString(R.string.trending_movies),
                                it.map { movie -> movie.toMovieItem() }),
                            MovieCategoryItem(
                                "Category 2",
                                listOf(
                                    MovieItem(12, "Title", null),
                                    ItemPlaceholder
                                )
                            )
                        )
                        moviesByCategories.postValue(movies)
                    },
                    {

                    })
        )
    }

}