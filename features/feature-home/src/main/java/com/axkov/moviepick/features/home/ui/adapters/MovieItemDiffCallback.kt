package com.axkov.moviepick.features.home.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.axkov.moviepick.features.home.ui.models.MovieItem

internal class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}