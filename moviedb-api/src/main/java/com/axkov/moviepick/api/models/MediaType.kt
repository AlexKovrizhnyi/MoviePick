package com.axkov.moviepick.api.models

enum class MediaType(private val type: String) {
    ALL("all"), MOVIE("movie"), TV("tv"), PERSON("person");

    override fun toString(): String {
        return type
    }
}