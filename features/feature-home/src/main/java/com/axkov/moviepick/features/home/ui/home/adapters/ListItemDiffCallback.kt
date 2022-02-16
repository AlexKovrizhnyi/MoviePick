package com.axkov.moviepick.features.home.ui.home.adapters

import androidx.recyclerview.widget.DiffUtil
import com.axkov.moviepick.features.home.ui.models.ListItem

internal class ListItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.equals(newItem)
    }
}