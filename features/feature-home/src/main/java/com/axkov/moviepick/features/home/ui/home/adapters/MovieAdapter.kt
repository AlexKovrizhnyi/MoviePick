package com.axkov.moviepick.features.home.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.ItemMovieBinding
import com.axkov.moviepick.features.home.ui.models.ListItem
import com.axkov.moviepick.features.home.ui.models.MovieItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

internal class MovieAdapter :
    ListAdapter<ListItem, RecyclerView.ViewHolder>(ListItemDiffCallback()) {

    private companion object {
        const val MOVIE_ITEM = 1
        const val UNKNOWN_TYPE = 404
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE_ITEM -> MovieViewHolder.from(parent)
            UNKNOWN_TYPE -> MoviePlaceholderViewHolder.from(parent)
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
//            else -> throw UnsupportedOperationException(
//                "Unknown item view type at position $position [${getItem(position)?.javaClass}]"
//            )
            else -> UNKNOWN_TYPE
        }
    }

    class MovieViewHolder private constructor(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItem) {
            binding.tvItemMovieTitle.text = item.title

            val resources = itemView.resources
            Glide.with(itemView)
                .load(item.poster)
                .transform(
                    CenterCrop(),
                    RoundedCorners(
                        resources
                            .getDimensionPixelOffset(R.dimen.movie_card_rounded_corners)
                    )
                )
//                .placeholder(R.drawable.bg_item_placeholder)
                .transition(withCrossFade())
                .into(binding.ivItemMoviePoster)
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }

    class MoviePlaceholderViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        companion object {
            fun from(parent: ViewGroup): MoviePlaceholderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_movie_placeholder, parent, false)
                return MoviePlaceholderViewHolder(view)
            }
        }
    }

}

