package com.axkov.moviepick.app.model

data class MovieCategoryItem(
    val title: String,
    val movieList: List<ListItem>
): ListItem {
    override val itemId: Long = title.hashCode().toLong()
}