package com.axkov.moviepick.features.home.ui.category

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import com.axkov.moviepick.core.ui.viewModel
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.FragmentCategoryBinding
import com.axkov.moviepick.features.home.di.HomeFeatureComponent
import com.axkov.moviepick.features.home.di.HomeFeatureComponentHolder

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val args: CategoryFragmentArgs by navArgs()

    private lateinit var binding: FragmentCategoryBinding

    private lateinit var diComponent: HomeFeatureComponent

    private lateinit var viewModelFactory: CategoryViewModel.Factory

    private val viewModel: CategoryViewModel by viewModel { viewModelFactory.create(args.category) }

    private var moviePagingAdapter: MoviePagingAdapter? = null

    override fun onAttach(context: Context) {
//        diComponent = ViewModelProvider(activity as ViewModelStoreOwner)
//            .get<HomeComponentHolder>().homeComponent
        diComponent = HomeFeatureComponentHolder.getComponent()
        viewModelFactory = diComponent.categoryViewModelFactory

        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryBinding.bind(view)

        moviePagingAdapter = MoviePagingAdapter()

        binding.rvCategoryMovies.apply {
//            layoutManager = GridLayoutManager(context, 2)
            adapter = moviePagingAdapter
        }

//        val moviesCategory = args.category
//        Timber.d("moviesCategory = $moviesCategory")

        viewModel.movies.observe(viewLifecycleOwner) {
            moviePagingAdapter?.submitData(lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        moviePagingAdapter = null
    }


}