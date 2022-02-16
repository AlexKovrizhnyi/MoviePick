package com.axkov.moviepick.domain.observers

import com.axkov.moviepick.domain.SubjectUseCase
import com.axkov.moviepick.domain.models.Movie
import com.axkov.moviepick.domain.repositories.PopularMoviesRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ObservePopularMovies @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) : SubjectUseCase<ObservePopularMovies.Params, List<Movie>>() {

    override fun createObservable(params: Params): Observable<List<Movie>> =
        popularMoviesRepository.observeForObservable(params.count, 0)

    data class Params(val count: Int = 20)
}
