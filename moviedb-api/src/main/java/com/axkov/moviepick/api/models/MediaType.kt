package com.axkov.moviepick.api.models

enum class MediaType {
    ALL, MOVIE, TV, PERSON;

    override fun toString(): String {
        return name.lowercase()
    }
}