package com.axkov.moviepick.app.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.app.R
import com.axkov.moviepick.app.model.MovieCategory

class MovieCategoryAdapter: RecyclerView.Adapter<MovieCategoryAdapter.MovieCategoryViewHolder>() {

    var data = listOf<MovieCategory>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryViewHolder {
       return MovieCategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieCategoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
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

        fun bind(item: MovieCategory) {
            categoryTitle.text = item.title
            movieAdapter.data = item.movieList
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