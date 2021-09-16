package com.axkov.moviepick.app.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.app.R
import com.axkov.moviepick.app.model.ItemPlaceholder
import com.axkov.moviepick.app.model.ListItem
import com.axkov.moviepick.app.model.MovieItem
import java.lang.ClassCastException

class MovieAdapter: ListAdapter<ListItem, RecyclerView.ViewHolder>(MovieItemDiffCallback()) {

    private companion object {
        const val MOVIE_ITEM = 1
        const val ITEM_PLACEHOLDER = 2
        const val UNKNOWN_TYPE = 404
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE_ITEM -> MovieViewHolder.from(parent)
            ITEM_PLACEHOLDER -> MoviePlaceholderViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                val item = getItem(position) as MovieItem
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieItem -> MOVIE_ITEM
            is ItemPlaceholder -> ITEM_PLACEHOLDER
            else -> UNKNOWN_TYPE
        }
    }

    class MovieViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.item_movie_tv_title)
        private val thumbnail: ImageView = itemView.findViewById(R.id.item_movie_iv_poster)

        fun bind(item: MovieItem) {
            title.text = item.title

            // TODO: Download image for MovieItem using Glide
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
                return MovieViewHolder(view)
            }
        }
    }

    class MoviePlaceholderViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        companion object {
            fun from(parent: ViewGroup): MoviePlaceholderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_movie_placeholder, parent, false)
                return MoviePlaceholderViewHolder(view)
            }
        }
    }
}

