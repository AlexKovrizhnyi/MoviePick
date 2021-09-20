package com.axkov.moviepick.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.axkov.moviepick.app.R
import com.axkov.moviepick.app.models.ItemPlaceholder
import com.axkov.moviepick.app.models.ListItem
import com.axkov.moviepick.app.models.MovieCategoryItem
import com.axkov.moviepick.app.models.MovieItem
import com.axkov.moviepick.app.ui.base.BaseViewModel
import com.axkov.moviepick.app.utils.ResourceProvider
import com.axkov.moviepick.core.network.di.NetworkComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val resources: ResourceProvider,
): BaseViewModel() {
    private val moviesByCategories = MutableLiveData<List<ListItem>>()

    private val api = NetworkComponent.createApiService()

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
        compositeDisposable.add(api.getTrendingWeek("0ff02fc51a7612e97b219cf796f5cd68")
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