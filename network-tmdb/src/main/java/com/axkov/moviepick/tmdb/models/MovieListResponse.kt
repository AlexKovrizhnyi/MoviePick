package com.axkov.moviepick.tmdb.models

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    val results: List<MovieDto>
)