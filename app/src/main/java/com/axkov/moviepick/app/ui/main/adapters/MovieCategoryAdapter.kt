package com.axkov.moviepick.app.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.app.R
import com.axkov.moviepick.app.models.ListItem
import com.axkov.moviepick.app.models.MovieCategoryItem

class MovieCategoryAdapter: ListAdapter<ListItem, MovieCategoryAdapter.MovieCategoryViewHolder>(MovieItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryViewHolder {
        return MovieCategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieCategoryViewHolder, position: Int) {
        val categoryItem = getItem(position) as MovieCategoryItem
        holder.bind(categoryItem)
    }

    class MovieCategoryViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val categoryTitle: TextView = itemView.findViewById(R.id.tv_category_title)
        private val categoryRecyclerView: RecyclerView = itemView.findViewById(R.id.category_recycler_view)
        private val movieAdapter = MovieAdapter()

        init {
            categoryRecyclerView.layoutManager = LinearLayoutManager(
                itemView.context,
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
                val view = layoutInflater.inflate(R.layout.item_movie_category_horizontal, parent, false)
                return MovieCategoryViewHolder(view)
            }
        }
    }
}