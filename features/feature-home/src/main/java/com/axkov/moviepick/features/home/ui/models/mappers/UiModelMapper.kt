package com.axkov.moviepick.features.home.ui.models.mappers

import com.axkov.moviepick.core.models.Movie
import com.axkov.moviepick.features.home.ui.models.MovieItem

//internal class MovieToMovieItem: Mapper<Movie, MovieItem> {
//
//    override fun map(input: Movie): MovieItem = MovieItem(
//        itemId = input.id,
//        title = input.title,
//        poster = input.poster
//    )
//}

internal fun Movie.toMovieItem() = MovieItem(
    itemId = id,
    title = title,
    overview = overview,
    poster = posterUrl
)