package com.axkov.moviepick.data.mappers

import com.axkov.moviepick.api.models.MovieDto
import com.axkov.moviepick.data.entities.MovieEntity

internal fun MovieDto.toEntity() = MovieEntity(
    tmdbId = id,
    title = title,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
//    runtime = runtime,
    voteCount = voteCount,
    voteAverage = voteAverage
)