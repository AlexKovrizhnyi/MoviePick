package com.axkov.moviepick.features.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.features.home.databinding.ItemMovieCategoryBinding
import com.axkov.moviepick.features.home.models.ListItem
import com.axkov.moviepick.features.home.models.MovieCategoryItem

class MovieCategoryAdapter : ListAdapter<ListItem, MovieCategoryAdapter.MovieCategoryViewHolder>(
    MovieItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryViewHolder {
        return MovieCategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieCategoryViewHolder, position: Int) {
        val categoryItem = getItem(position) as MovieCategoryItem
        holder.bind(categoryItem)
    }

    class MovieCategoryViewHolder private constructor(private val binding: ItemMovieCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val movieAdapter = MovieAdapter()

        init {
            binding.categoryRecyclerView.apply {
                layoutManager = LinearLayoutManager(
                    binding.root.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = movieAdapter
            }
        }

        fun bind(item: MovieCategoryItem) {
            binding.tvCategoryTitle.text = item.title
            movieAdapter.submitList(item.movieList)
        }

        companion object {
            fun from(parent: ViewGroup): MovieCategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemMovieCategoryBinding.inflate(layoutInflater, parent, false)
                return MovieCategoryViewHolder(binding)
            }
        }
    }
}