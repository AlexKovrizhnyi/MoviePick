package com.axkov.moviepick.tmdb.enums

enum class MediaType {
    ALL, MOVIE, TV, PERSON;

    override fun toString(): String {
        return name.lowercase()
    }
}