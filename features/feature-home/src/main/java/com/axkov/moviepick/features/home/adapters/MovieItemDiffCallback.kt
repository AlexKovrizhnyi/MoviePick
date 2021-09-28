package com.axkov.moviepick.features.home.adapters

import androidx.recyclerview.widget.DiffUtil
import com.axkov.moviepick.features.home.models.ListItem

class MovieItemDiffCallback: DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.equals(newItem)
    }
}