package com.axkov.moviepick.features.home.ui.home

import com.axkov.moviepick.features.home.ui.models.ListItem

internal data class HomeViewState(
    val popularItems: List<ListItem> = listOf(),
    val topRatedItems: List<ListItem> = listOf(),
    val upcomingItems: List<ListItem> = listOf(),
    val isLoading: Boolean = false,
) {

    companion object {
        fun loading() = HomeViewState(isLoading = true)
    }

}