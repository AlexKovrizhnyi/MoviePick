package com.axkov.moviepick.features.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.axkov.moviepick.api.TmdbApi
import com.axkov.moviepick.core.ui.BaseViewModel
import com.axkov.moviepick.core.utils.ResourceProvider
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.ui.models.ItemPlaceholder
import com.axkov.moviepick.features.home.ui.models.ListItem
import com.axkov.moviepick.features.home.ui.models.MovieCategoryItem
import com.axkov.moviepick.features.home.ui.models.MovieItem
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val resources: ResourceProvider,
    private val api: TmdbApi,
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
        compositeDisposable.add(api.trending.getTrendingWeek()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    val movies = listOf(
                        MovieCategoryItem(
                            resources.getString(R.string.trending_movies),
                            it.results.map { movieDto ->
                                MovieItem(movieDto.id, movieDto.title, movieDto.poster)
                            }),
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

                }
            ))
    }

}