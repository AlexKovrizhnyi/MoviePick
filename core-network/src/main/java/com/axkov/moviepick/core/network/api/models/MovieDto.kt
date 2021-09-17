package com.axkov.moviepick.core.network.api.models

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")  val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val poster: String?
)