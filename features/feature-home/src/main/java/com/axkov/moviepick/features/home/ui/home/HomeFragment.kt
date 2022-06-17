package com.axkov.moviepick.features.home.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.axkov.moviepick.core.enums.MoviesCategory
import com.axkov.moviepick.core.ui.viewModel
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.FragmentHomeScreenBinding
import com.axkov.moviepick.features.home.di.HomeFeatureComponent
import com.axkov.moviepick.features.home.di.HomeFeatureComponentHolder
import com.axkov.moviepick.features.home.ui.home.adapters.MovieAdapter
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    private lateinit var diComponent: HomeFeatureComponent

    private val viewModel by viewModel { diComponent.homeViewModel }

    private var popularAdapter: MovieAdapter? = null
    private var topRatedAdapter: MovieAdapter? = null
    private var upcomingAdapter: MovieAdapter? = null

    override fun onAttach(context: Context) {
        diComponent = HomeFeatureComponentHolder.getComponent()

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeScreenBinding.bind(view)

        initUi()
        observeData()
    }

    private fun initUi() {

        binding.tvShowMorePopular.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToCategoryFragment(MoviesCategory.POPULAR)
            findNavController().navigate(action)
        }

        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.refresh() }

        popularAdapter = MovieAdapter()
        topRatedAdapter = MovieAdapter()
        upcomingAdapter = MovieAdapter()

        binding.rvPopularMovies.adapter = popularAdapter
        binding.rvTopRatedMovies.adapter = topRatedAdapter
        binding.rvUpcomingMovies.adapter = upcomingAdapter
    }

    private fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: HomeViewState) {
        showLoadingState(state)

        popularAdapter?.submitList(state.popular.items)
        topRatedAdapter?.submitList(state.topRated.items)
        upcomingAdapter?.submitList(state.upcoming.items)

        if (state.messages.isNotEmpty()) {
            state.messages.firstOrNull()?.let {
                showSnackBar(it.message)
                viewModel.messageShown(it.id)
            }
        }
    }

    private fun destroyAdapters() {
        popularAdapter = null
        topRatedAdapter = null
        upcomingAdapter = null
    }

    override fun onDestroyView() {
        super.onDestroyView()

        destroyAdapters()
    }

    private fun showLoadingState(state: HomeViewState) {
        val isLoading = state.popular.loading
                || state.topRated.loading
                || state.upcoming.loading

        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}