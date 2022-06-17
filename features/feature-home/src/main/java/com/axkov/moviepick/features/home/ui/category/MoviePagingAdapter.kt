package com.axkov.moviepick.features.home.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.axkov.moviepick.core.models.Movie
import com.axkov.moviepick.features.home.R
import com.axkov.moviepick.features.home.databinding.ItemMovieCategoryBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

internal class MoviePagingAdapter :
    PagingDataAdapter<Movie, RecyclerView.ViewHolder>(COMPARATOR) {

    private companion object {
        const val MOVIE_ITEM = 1
        const val UNKNOWN_TYPE = 404

        val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.equals(newItem)
            }
        }
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
                val item = getItem(position) as Movie
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Movie -> MOVIE_ITEM
//            else -> throw UnsupportedOperationException(
//                "Unknown item view type at position $position [${getItem(position)?.javaClass}]"
//            )
            else -> UNKNOWN_TYPE
        }
    }

    class MovieViewHolder private constructor(private val binding: ItemMovieCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.tvItemMovieCategoryTitle.text = item.title
            binding.tvItemMovieCategoryOverview.text = item.overview
            binding.rbItemMovieCategoryRating.rating = (item.voteAverage / 2.0).toFloat()

            val resources = itemView.resources
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${item.posterPath}")
                .transform(
                    CenterCrop(),
                    RoundedCorners(
                        resources
                            .getDimensionPixelOffset(R.dimen.movie_card_rounded_corners)
                    )
                )
//                .placeholder(R.drawable.bg_item_placeholder)
                .transition(withCrossFade())
                .into(binding.ivItemMovieCategoryPoster)
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieCategoryBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolder(binding)
            }
        }
    }

    class MoviePlaceholderViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        companion object {
            fun from(parent: ViewGroup): MoviePlaceholderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_movie_category, parent, false)
                return MoviePlaceholderViewHolder(view)
            }
        }
    }
}

