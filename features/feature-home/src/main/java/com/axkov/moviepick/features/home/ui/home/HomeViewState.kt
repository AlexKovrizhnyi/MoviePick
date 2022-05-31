package com.axkov.moviepick.features.home.ui.home

import com.axkov.moviepick.core.ui.UiMessage
import com.axkov.moviepick.features.home.ui.models.ListItem
import com.axkov.moviepick.features.home.ui.models.MovieItem

internal data class HomeViewState(
    val popularItems: List<ListItem> = listOf(),
    val topRatedItems: List<ListItem> = listOf(),
    val upcomingItems: List<ListItem> = listOf(),
    val popularLoading: Boolean = false,
    val topRatedLoading: Boolean = false,
    val upcomingLoading: Boolean = false,
    val messages: List<UiMessage> = listOf()
)

internal sealed interface PartialStateChanges {
    data class MessageShown(val messageId: Long) : PartialStateChanges

    object PopularMoviesLoading : PartialStateChanges

    data class PopularMoviesError(val message: UiMessage) : PartialStateChanges

    data class PopularMoviesLoaded(val content: List<MovieItem>) : PartialStateChanges

    object TopRatedMoviesLoading : PartialStateChanges

    data class TopRatedMoviesError(val message: UiMessage) : PartialStateChanges

    data class TopRatedMoviesLoaded(val content: List<MovieItem>) : PartialStateChanges

}