package com.axkov.moviepick.app.models

data class MovieItem(
    override val itemId: Long,
    val title: String,
    val poster: String?
): ListItem