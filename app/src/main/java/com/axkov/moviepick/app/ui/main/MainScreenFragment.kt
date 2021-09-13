package com.axkov.moviepick.app.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.app.R
import com.axkov.moviepick.app.model.ItemPlaceholder
import com.axkov.moviepick.app.model.ListItem
import com.axkov.moviepick.app.model.MovieCategory
import com.axkov.moviepick.app.model.MovieItem
import com.axkov.moviepick.app.ui.base.BaseFragment
import com.axkov.moviepick.app.ui.main.adapters.MovieCategoryAdapter

class MainScreenFragment: BaseFragment(R.layout.fragment_main_screen) {
    private lateinit var viewModel: ViewModel
    private lateinit var movieCategoryAdapter: MovieCategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieCategoryAdapter = MovieCategoryAdapter()
        movieCategoryAdapter.data = getCategories()
        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_main_screen_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = movieCategoryAdapter
    }

    private fun getCategories(): List<MovieCategory> = listOf(
        MovieCategory("Category 1", listOf(
            MovieItem(1, "Movie 1", null),
            MovieItem(2, "Movie 2", null),
            MovieItem(3, "Movie 3", null),
            MovieItem(4, "Movie 4", null),
            MovieItem(5, "Movie 5", null),

        )),
        MovieCategory("Category 2", listOf(
            MovieItem(1, "Movie 1", null),
            MovieItem(2, "Movie 2", null),
            ItemPlaceholder,
            ItemPlaceholder
        )),
        MovieCategory("Category 3", listOf(
            ItemPlaceholder,
            ItemPlaceholder,
            MovieItem(3, "Movie 3", null),
            ItemPlaceholder
        ))
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }
}