package com.axkov.moviepick.data.mappers

import com.axkov.moviepick.data.entities.MovieEntity
import com.axkov.moviepick.domain.models.Movie

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