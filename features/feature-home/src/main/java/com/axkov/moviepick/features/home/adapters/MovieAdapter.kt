package com.axkov.moviepick.features.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.ItemMovieBinding
import com.axkov.moviepick.features.home.models.ItemPlaceholder
import com.axkov.moviepick.features.home.models.ListItem
import com.axkov.moviepick.features.home.models.MovieItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
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

    class MovieViewHolder private constructor(binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        private val title: TextView = binding.itemMovieTvTitle
        private val poster: ImageView = binding.itemMovieIvPoster

        fun bind(item: MovieItem) {
            title.text = item.title

            val resources = itemView.resources
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${item.poster}")
//                .override(
//                    resources.getDimensionPixelOffset(R.dimen.movie_card_width),
//                    resources.getDimensionPixelOffset(R.dimen.movie_card_height)
//                )
                .transform(
                    CenterCrop(),
                    RoundedCorners(resources
                        .getDimensionPixelOffset(R.dimen.movie_card_rounded_corners))
                )
                .transition(withCrossFade())
                .into(poster)
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
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

