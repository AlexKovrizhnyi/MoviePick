package com.axkov.moviepick.features.home.models

data class MovieCategoryItem(
    val title: String,
    val movieList: List<ListItem>
): ListItem {
    override val itemId: Long = title.hashCode().toLong()
}