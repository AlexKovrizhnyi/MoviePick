package com.axkov.moviepick.core.utils.mappers

import com.axkov.moviepick.api.models.MovieDto
import com.axkov.moviepick.domain.models.Movie

fun MovieDto.toMovie(): Movie = Movie(
    id = this.id,
    title = this.title,
    originalTitle = this.originalTitle,
    overview = this.overview,
    posterPath = this.posterPath ?: "",
    backdropPath = this.backdropPath ?: "",
//    runtime = this.runtime,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
) // TODO: Add default value for the poster