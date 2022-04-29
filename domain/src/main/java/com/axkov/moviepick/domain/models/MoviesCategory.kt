package com.axkov.moviepick.domain.models

object CategoryString {
    const val TRENDING = "trending"
    const val POPULAR = "popular"
    const val TOP_RATED = "top_rated"
    const val UPCOMING = "upcoming"
}

enum class MoviesCategory {
    POPULAR, TOP_RATED, UPCOMING
}