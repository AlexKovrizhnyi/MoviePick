package com.axkov.moviepick.data

import com.axkov.moviepick.api.models.MovieDto
import com.axkov.moviepick.data.entities.MovieEntity

internal fun MovieDto.toEntity(category: String? = null) = MovieEntity(
    tmdbId = this.id,
    title = this.title,
    originalTitle = this.originalTitle,
    category = category,
    overview = this.overview,
    posterPath = this.posterPath,
//    runtime = this.runtime,
    voteCount = this.voteCount,
    voteAverage = this.voteAverage
)