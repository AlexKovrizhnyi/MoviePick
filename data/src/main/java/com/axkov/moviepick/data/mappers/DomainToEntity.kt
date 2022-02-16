package com.axkov.moviepick.data

import com.axkov.moviepick.data.entities.MovieEntity
import com.axkov.moviepick.domain.models.Movie

fun Movie.toEntity(category: String? = null): MovieEntity = MovieEntity(
    tmdbId = this.id,
    title = this.title,
    originalTitle = this.originalTitle,
    category = category,
    overview = this.overview,
    posterPath = this.posterPath,
    backdropPath = this.backdropPath,
//    runtime = this.runtime,
    voteCount = this.voteCount,
    voteAverage = this.voteAverage
)

fun MovieEntity.toDomain(): Movie = Movie(
    id = this.tmdbId ?: -1,
    title = this.title ?: "",
    originalTitle = this.originalTitle ?: "",
    overview = this.overview ?: "",
    posterPath = this.posterPath ?: "",
    backdropPath = this.backdropPath ?: "",
//    runtime = this.runtime,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)