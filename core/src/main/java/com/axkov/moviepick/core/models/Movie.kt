package com.axkov.moviepick.core.models

data class Movie(
    val id: Long,

    val title: String,

    val originalTitle: String,

    val overview: String,

    val posterPath: String,

    val backdropPath: String,

//    val runtime: Int,

    val voteAverage: Double,

    val voteCount: Int
) {

    val posterUrl = "https://image.tmdb.org/t/p/w342$posterPath"
}