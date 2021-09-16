package com.axkov.moviepick.app.model

data class MovieItem(
    override val itemId: Long,
    val title: String,
    val thumbnail: String?
): ListItem