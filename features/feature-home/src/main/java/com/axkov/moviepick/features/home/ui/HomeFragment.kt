package com.axkov.moviepick.features.home.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.axkov.moviepick.core.ui.viewModel
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.FragmentHomeScreenBinding
import com.axkov.moviepick.features.home.di.HomeComponent
import com.axkov.moviepick.features.home.di.HomeComponentHolder
import com.axkov.moviepick.features.home.ui.adapters.MovieAdapter

class HomeFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    private lateinit var diComponent: HomeComponent

    private val viewModel by viewModel { diComponent.homeViewModel }

    private var popularAdapter: MovieAdapter? = null
    private var topRatedAdapter: MovieAdapter? = null
    private var upcomingAdapter: MovieAdapter? = null

    override fun onAttach(context: Context) {
        diComponent = ViewModelProvider(this)
            .get<HomeComponentHolder>().homeComponent

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeScreenBinding.bind(view)

        initUi()
    }

    private fun initUi() {
        popularAdapter = MovieAdapter()
        topRatedAdapter = MovieAdapter()
        upcomingAdapter = MovieAdapter()

        binding.rvPopularMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }

        binding.rvTopRatedMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
        }

        binding.rvUpcomingMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingAdapter
        }

        viewModel.popular.observe(viewLifecycleOwner) {
            popularAdapter?.submitData(lifecycle, it)
        }

        viewModel.topRated.observe(viewLifecycleOwner) {
            topRatedAdapter?.submitData(lifecycle, it)
        }

        viewModel.upcoming.observe(viewLifecycleOwner) {
            upcomingAdapter?.submitData(lifecycle, it)
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

//    private fun showError(errorMessage: String) {
//        Timber.e("An error occurred: $errorMessage")
//    }
//
//    private fun showLoading(isLoading: Boolean = false) {
////        if (isLoading) {
////            binding.loadingContainer.loadingContainer.visibility = View.VISIBLE
////        } else {
////            binding.loadingContainer = View.GONE
////        }
//    }
}