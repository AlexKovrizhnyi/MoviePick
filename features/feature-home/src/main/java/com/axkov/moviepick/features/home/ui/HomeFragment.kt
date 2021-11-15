package com.axkov.moviepick.features.home.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.core.ui.BaseFragment
import com.axkov.moviepick.core.ui.viewModel
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.FragmentHomeScreenBinding
import com.axkov.moviepick.features.home.di.HomeComponent
import com.axkov.moviepick.features.home.di.HomeComponentHolder
import com.axkov.moviepick.features.home.ui.adapters.MovieCategoryAdapter

class HomeFragment : BaseFragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    private lateinit var diComponent: HomeComponent

    private val viewModel by viewModel { diComponent.homeViewModel }

    private var movieCategoryAdapter: MovieCategoryAdapter? = null


    override fun onAttach(context: Context) {
        diComponent = ViewModelProvider(this)
            .get<HomeComponentHolder>().homeComponent

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeScreenBinding.bind(view)

        movieCategoryAdapter = MovieCategoryAdapter()
        movieCategoryAdapter?.submitList(viewModel.getLoaders())

        val recyclerView: RecyclerView = binding.fragmentMainScreenRecyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = movieCategoryAdapter

        viewModel.getMovies().observe(viewLifecycleOwner) {
            movieCategoryAdapter?.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        movieCategoryAdapter = null
    }
}