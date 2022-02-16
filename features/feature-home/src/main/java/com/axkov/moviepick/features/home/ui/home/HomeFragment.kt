package com.axkov.moviepick.features.home.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.axkov.moviepick.core.ui.viewModel
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.FragmentHomeScreenBinding
import com.axkov.moviepick.features.home.di.HomeComponent
import com.axkov.moviepick.features.home.di.HomeComponentHolder
import com.axkov.moviepick.features.home.ui.home.adapters.MovieAdapter

class HomeFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    private lateinit var diComponent: HomeComponent

    private val viewModel by viewModel { diComponent.homeViewModel }

    private var popularAdapter: MovieAdapter? = null
    private var topRatedAdapter: MovieAdapter? = null
    private var upcomingAdapter: MovieAdapter? = null

    override fun onAttach(context: Context) {
        diComponent = ViewModelProvider(activity as ViewModelStoreOwner)
            .get<HomeComponentHolder>().homeComponent

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeScreenBinding.bind(view)

        initUi()
    }

    private fun initUi() {

        binding.tvShowMorePopular.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment)
        }

        popularAdapter = MovieAdapter()
        topRatedAdapter = MovieAdapter()
        upcomingAdapter = MovieAdapter()

        binding.rvPopularMovies.adapter = popularAdapter
        binding.rvTopRatedMovies.adapter = popularAdapter
        binding.rvUpcomingMovies.adapter = popularAdapter

        viewModel.popular.observe(viewLifecycleOwner) {
            popularAdapter?.submitList(it)
            topRatedAdapter?.submitList(it)
            upcomingAdapter?.submitList(it)
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