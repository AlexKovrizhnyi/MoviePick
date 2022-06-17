package com.axkov.moviepick.data.mappers

import com.axkov.moviepick.tmdb.models.MovieDto
import com.axkov.moviepick.database.entities.MovieEntity

fun MovieDto.toEntity() = MovieEntity(
    tmdbId = id,
    title = title,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
//    runtime = runtime,
    voteCount = voteCount,
    voteAverage = voteAverage
)