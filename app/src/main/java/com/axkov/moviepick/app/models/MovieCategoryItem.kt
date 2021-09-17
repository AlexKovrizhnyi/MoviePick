package com.axkov.moviepick.app.models

data class MovieCategoryItem(
    val title: String,
    val movieList: List<ListItem>
): ListItem {
    override val itemId: Long = title.hashCode().toLong()
}