package com.axkov.moviepick.features.home.domain.usecases

import com.axkov.moviepick.features.home.data.repositories.MoviesPagingRepositoryImpl
import javax.inject.Inject

internal class GetPopularMovies @Inject constructor(
    private val moviesRepository: MoviesPagingRepositoryImpl
) {
    operator fun invoke() = moviesRepository.getPopularMovies()
}



