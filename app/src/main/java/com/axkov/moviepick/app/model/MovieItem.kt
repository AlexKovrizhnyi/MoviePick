package com.axkov.moviepick.app.model

data class MovieItem(
    val id: Int,
    val title: String,
    val thumbnail: String?
): ListItem
