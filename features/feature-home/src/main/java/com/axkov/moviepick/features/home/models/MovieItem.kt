package com.axkov.moviepick.features.home.models

data class MovieItem(
    override val itemId: Long,
    val title: String,
    val poster: String?
): ListItem