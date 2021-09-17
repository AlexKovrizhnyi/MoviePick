package com.axkov.moviepick.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.axkov.moviepick.app.models.ItemPlaceholder
import com.axkov.moviepick.app.models.ListItem
import com.axkov.moviepick.app.models.MovieCategoryItem
import com.axkov.moviepick.app.models.MovieItem
import com.axkov.moviepick.app.ui.base.BaseViewModel
import com.axkov.moviepick.core.network.di.NetworkComponent
import io.reactivex.rxjava3.schedulers.Schedulers

class MainScreenViewModel: BaseViewModel() {
    private val moviesByCategories = MutableLiveData<List<ListItem>>()

    private val api = NetworkComponent.createApi()

    fun getLoaders(): List<ListItem> {
        return listOf(
            MovieCategoryItem(
                "Trending this week",
                movieList = IntRange(1, 3).map { ItemPlaceholder }
            ),
            MovieCategoryItem(
                "Category 2",
                movieList = IntRange(1, 3).map { ItemPlaceholder }
            ),
            MovieCategoryItem(
                "Category 3",
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
                    val movies = listOf(MovieCategoryItem("Trending this week",
                        it.results.map { movieDto ->
                        MovieItem(movieDto.id, movieDto.title, movieDto.poster)
                    }),
                    MovieCategoryItem("Category 2", listOf(
                        MovieItem(12, "Title", null),
                        ItemPlaceholder
                    )))
                    moviesByCategories.postValue(movies)
                },
                {

                }
            ))
    }

}