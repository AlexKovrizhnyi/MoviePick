package com.axkov.moviepick.database.mappers

import com.axkov.moviepick.core.models.Movie
import com.axkov.moviepick.database.entities.MovieEntity

fun Movie.toEntity(): MovieEntity = MovieEntity(
    tmdbId = id,
    title = title,
    originalTitle = originalTitle,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
//    runtime = runtime,
    voteCount = voteCount,
    voteAverage = voteAverage
)

fun MovieEntity.toDomain(): Movie = Movie(
    id = tmdbId,
    title = title ?: "",
    originalTitle = originalTitle ?: "",
    overview = overview ?: "",
    posterPath = posterPath ?: "",
    backdropPath = backdropPath ?: "",
//    runtime = runtime,
    voteAverage = voteAverage,
    voteCount = voteCount
)