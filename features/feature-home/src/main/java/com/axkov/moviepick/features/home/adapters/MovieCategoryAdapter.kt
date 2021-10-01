package com.axkov.moviepick.features.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.features.home.databinding.ItemMovieCategoryHorizontalBinding
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

    class MovieCategoryViewHolder private constructor(binding: ItemMovieCategoryHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val categoryTitle: TextView = binding.tvCategoryTitle
        private val categoryRecyclerView: RecyclerView = binding.categoryRecyclerView
        private val movieAdapter = MovieAdapter()

        init {
            categoryRecyclerView.layoutManager = LinearLayoutManager(
                binding.root.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            categoryRecyclerView.adapter = movieAdapter
        }

        fun bind(item: MovieCategoryItem) {
            categoryTitle.text = item.title
            movieAdapter.submitList(item.movieList)
        }

        companion object {
            fun from(parent: ViewGroup): MovieCategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemMovieCategoryHorizontalBinding.inflate(layoutInflater, parent, false)
                return MovieCategoryViewHolder(binding)
            }
        }
    }
}