package com.axkov.moviepick.tmdb.enums

enum class MovieList {
    POPULAR, TOP_RATED, UPCOMING;

    override fun toString(): String {
        return name.lowercase()
    }
}