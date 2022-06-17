package com.axkov.moviepick.features.home.domain.observers

import com.axkov.moviepick.core.data.Data
import com.axkov.moviepick.core.SubjectUseCase
import com.axkov.moviepick.core.models.Movie
import com.axkov.moviepick.data.repositories.PopularMoviesRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ObservePopularMovies @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) : SubjectUseCase<ObservePopularMovies.Params, Data<List<Movie>>>() {

//) : SubjectUseCase<ObservePopularMovies.Params, List<Movie>>() {

    override fun createObservable(params: Params): Observable<Data<List<Movie>>> =
        popularMoviesRepository.observeForObservable(params.count, 0)

//    override fun createObservable(params: Params): Observable<List<Movie>> =
//        popularMoviesRepository.observeForObservable(params.count, 0)

    data class Params(val count: Int = 20)
}