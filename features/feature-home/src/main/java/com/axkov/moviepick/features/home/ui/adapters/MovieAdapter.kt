package com.axkov.moviepick.features.home.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.ItemMovieBinding
import com.axkov.moviepick.features.home.ui.models.MovieItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

internal class MovieAdapter :
    PagingDataAdapter<MovieItem, RecyclerView.ViewHolder>(MovieItemDiffCallback()) {

    private companion object {
        const val MOVIE_ITEM = 1
        const val UNKNOWN_TYPE = 404
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE_ITEM -> MovieViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType: $viewType")
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
            else -> throw UnsupportedOperationException(
                "Unknown item view type at position $position [${getItem(position)?.javaClass}]"
            )
        }
    }

    class MovieViewHolder private constructor(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItem) {
            binding.itemMovieTvTitle.text = item.title

            val resources = itemView.resources
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${item.poster}")
                .transform(
                    CenterCrop(),
                    RoundedCorners(
                        resources
                            .getDimensionPixelOffset(R.dimen.movie_card_rounded_corners)
                    )
                )
//                .placeholder(R.drawable.bg_item_placeholder)
                .transition(withCrossFade())
                .into(binding.itemMovieIvPoster)
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }
}

