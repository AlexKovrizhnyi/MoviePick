package com.axkov.moviepick.domain.models

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
)