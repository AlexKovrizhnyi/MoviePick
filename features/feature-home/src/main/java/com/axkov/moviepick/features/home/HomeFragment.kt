package com.axkov.moviepick.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.core.ui.BaseFragment
import com.axkov.moviepick.features.home.adapters.MovieCategoryAdapter
import com.axkov.moviepick.features.home.di.HomeScreenComponentProvider

class HomeFragment : BaseFragment(R.layout.fragment_main_screen) {
    private val component by lazy {
        (requireActivity().application as HomeScreenComponentProvider)
            .getHomeScreenComponent()
    }

    private val viewModel by viewModels<HomeViewModel> { component.viewModelFactory() }
    private var movieCategoryAdapter: MovieCategoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieCategoryAdapter = MovieCategoryAdapter()
        movieCategoryAdapter?.submitList(viewModel.getLoaders())

        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_main_screen_recycler_view)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = movieCategoryAdapter

        viewModel.getMovies().observe(viewLifecycleOwner, {
            movieCategoryAdapter?.submitList(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieCategoryAdapter = null
    }
}