package com.axkov.moviepick.features.home.ui.models

internal data class MovieItem(
    override val itemId: Long,
    val title: String,
    val poster: String?
): ListItem