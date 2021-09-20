package com.axkov.moviepick.core.network.api.models

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    val results: List<MovieDto>
)