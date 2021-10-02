package com.axkov.moviepick.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.core.ui.BaseFragment
import com.axkov.moviepick.features.home.adapters.MovieCategoryAdapter
import com.axkov.moviepick.features.home.databinding.FragmentHomeScreenBinding
import com.axkov.moviepick.features.home.di.HomeScreenComponentBuilderProvider

class HomeFragment : BaseFragment(R.layout.fragment_home_screen) {
    private val component by lazy {
        (requireActivity().application as HomeScreenComponentBuilderProvider).get().build()
    }

    private lateinit var binding: FragmentHomeScreenBinding

    private val viewModel by viewModels<HomeViewModel> { component.viewModelFactory() }

    private var movieCategoryAdapter: MovieCategoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeScreenBinding.bind(view)

        movieCategoryAdapter = MovieCategoryAdapter()
        movieCategoryAdapter?.submitList(viewModel.getLoaders())

        val recyclerView: RecyclerView = binding.fragmentMainScreenRecyclerView
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