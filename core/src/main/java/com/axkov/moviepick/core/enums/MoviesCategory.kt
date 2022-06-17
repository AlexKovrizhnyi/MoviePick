package com.axkov.moviepick.core.enums

enum class MoviesCategory {
    POPULAR, TOP_RATED, UPCOMING;

    override fun toString(): String {
        return name.lowercase()
    }
}