package com.axkov.moviepick.features.home.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.axkov.moviepick.core.ui.Event
import com.axkov.moviepick.core.ui.viewModel
import com.axkov.moviepick.domain.models.MoviesCategory
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.FragmentHomeScreenBinding
import com.axkov.moviepick.features.home.di.HomeComponent
import com.axkov.moviepick.features.home.di.HomeComponentHolder
import com.axkov.moviepick.features.home.ui.home.adapters.MovieAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    private lateinit var diComponent: HomeComponent

    private val viewModel by viewModel { diComponent.homeViewModel }

    private var popularAdapter: MovieAdapter? = null
    private var topRatedAdapter: MovieAdapter? = null
    private var upcomingAdapter: MovieAdapter? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        diComponent = ViewModelProvider(activity as ViewModelStoreOwner)
            .get<HomeComponentHolder>().homeComponent

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
//            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment)
            viewModel.handleAction(HomeViewAction.OnShowMoreCategoryClick(MoviesCategory.POPULAR))
        }

        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.handleAction(HomeViewAction.Refresh) }

        popularAdapter = MovieAdapter()
        topRatedAdapter = MovieAdapter()
        upcomingAdapter = MovieAdapter()

        binding.rvPopularMovies.adapter = popularAdapter
        binding.rvTopRatedMovies.adapter = topRatedAdapter
        binding.rvUpcomingMovies.adapter = upcomingAdapter

        compositeDisposable.add(
            viewModel.uiEvent.subscribe(::handleEvent)
        )
//        viewModel.popular.observe(viewLifecycleOwner) {
//            popularAdapter?.submitList(it)
//            topRatedAdapter?.submitList(it)
//            upcomingAdapter?.submitList(it)
//        }

//        viewModel.uiState.observe(viewLifecycleOwner) {
//            renderViewState(it)
//        }
    }

    private fun handleEvent(event: Event<HomeViewEvent>) {
        when (val specificEvent = event.getContentIfNotHandled()) {
            is HomeViewEvent.ShowToast -> showToast(specificEvent.message)
            is HomeViewEvent.NavigateToCategory -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToCategoryFragment(specificEvent.category)
                findNavController().navigate(action)
            }
            HomeViewEvent.NavigateToDetails -> TODO("Implement `navigate to details` event")
            is HomeViewEvent.ShowSnackBar -> TODO("Implement `show snackbar` event")
            null -> TODO()
        }
    }

    private fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            renderViewState(it)
        }

//        viewModel.uiState.map { it.isLoading }
//            .distinctUntilChanged()
//            .observe(viewLifecycleOwner, Observer {
//                showLoading(it)
//            })
    }

    private fun renderViewState(state: HomeViewState) {
//        if (state.isLoading) {
//            // ver. 2
//            showLoading(true)
//            return

//            // ver. 1
//            binding.loading.loadingContainer.visibility = View.VISIBLE
//            return
//        }
//        binding.loading.loadingContainer.visibility = View.GONE

        showLoading(state.isLoading)

        popularAdapter?.submitList(state.popularItems)
        topRatedAdapter?.submitList(state.topRatedItems)
        upcomingAdapter?.submitList(state.upcomingItems)
    }

    private fun destroyAdapters() {
        popularAdapter = null
        topRatedAdapter = null
        upcomingAdapter = null
    }

    override fun onDestroyView() {
        super.onDestroyView()

        destroyAdapters()
        compositeDisposable.clear()
    }

    //    private fun showError(errorMessage: String) {
//        Timber.e("An error occurred: $errorMessage")
//    }
//
    private fun showLoading(isLoading: Boolean) {
        binding.loading.loadingContainer.visibility = if (isLoading) View.VISIBLE else View.GONE

        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun showSnackBar(message: String) {
//        Snackbar.make(context, message, Snackbar.LENGTH_LONG)
    }
}