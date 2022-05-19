package com.axkov.moviepick.data.mappers

import com.axkov.moviepick.api.models.MovieDto
import com.axkov.moviepick.core.domain.models.Movie

fun MovieDto.toDomain() = Movie(
    id = id,
    title = title,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath ?: "",
    backdropPath = backdropPath ?: "",
    voteAverage = voteAverage,
    voteCount = voteCount
)