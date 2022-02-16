package com.axkov.moviepick.features.home.ui.category

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import com.axkov.moviepick.core.ui.viewModel
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.FragmentCategoryBinding
import com.axkov.moviepick.features.home.di.HomeComponent
import com.axkov.moviepick.features.home.di.HomeComponentHolder

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding

    private lateinit var diComponent: HomeComponent

    private val viewModel: CategoryViewModel by viewModel { diComponent.categoryViewModel }

    private var moviePagingAdapter: MoviePagingAdapter? = null

    override fun onAttach(context: Context) {
        diComponent = ViewModelProvider(activity as ViewModelStoreOwner)
            .get<HomeComponentHolder>().homeComponent

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

        viewModel.category.observe(viewLifecycleOwner) {
            moviePagingAdapter?.submitData(lifecycle, it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        moviePagingAdapter = null
    }


}