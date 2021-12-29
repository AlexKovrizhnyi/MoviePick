package com.axkov.moviepick.features.home.domain.usecases

import com.axkov.moviepick.features.home.data.repositories.MoviesPagingRepositoryImpl
import javax.inject.Inject


internal class GetTrendingMovies @Inject constructor(
    private val MoviesRepository: MoviesPagingRepositoryImpl
) {
    operator fun invoke() = MoviesRepository.getTrendingMovies()
}