package com.axkov.moviepick.features.home.ui.models.mappers

import com.axkov.moviepick.domain.models.Movie
import com.axkov.moviepick.features.home.ui.models.MovieItem

//internal class MovieToMovieItem: Mapper<Movie, MovieItem> {
//
//    override fun map(input: Movie): MovieItem = MovieItem(
//        itemId = input.id,
//        title = input.title,
//        poster = input.poster
//        // TODO: Maybe add url to the poster string before the image id (or do it in MovieDto). Poster must be url-prepared in a string variable
//    )
//}

internal fun Movie.toMovieItem() = MovieItem(
    itemId = id,
    title = title,
    overview = overview,
    poster = posterPath
)