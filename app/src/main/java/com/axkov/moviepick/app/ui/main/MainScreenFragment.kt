package com.axkov.moviepick.app.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.app.R
import com.axkov.moviepick.app.di.MainScreenComponent
import com.axkov.moviepick.app.ui.base.BaseFragment
import com.axkov.moviepick.app.ui.main.adapters.MovieCategoryAdapter

class MainScreenFragment : BaseFragment(R.layout.fragment_main_screen) {
    private val component by lazy {
        MainScreenComponent.create(requireContext())
    }

    private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }
    private var movieCategoryAdapter: MovieCategoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieCategoryAdapter = MovieCategoryAdapter()
        movieCategoryAdapter.submitList(viewModel.getLoaders())

        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_main_screen_recycler_view)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = movieCategoryAdapter

        viewModel.getMovies().observe(viewLifecycleOwner, {
            movieCategoryAdapter.submitList(it)
        })
    }
}