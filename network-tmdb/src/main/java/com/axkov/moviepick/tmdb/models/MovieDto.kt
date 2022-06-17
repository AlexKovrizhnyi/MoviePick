package com.axkov.moviepick.tmdb.models

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Long,

    val title: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

//    val runtime: Int,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)