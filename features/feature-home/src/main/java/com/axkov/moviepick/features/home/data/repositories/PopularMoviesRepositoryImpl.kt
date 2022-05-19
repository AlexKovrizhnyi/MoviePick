package com.axkov.moviepick.features.home.data.repositories

import com.axkov.moviepick.api.models.MovieDto
import com.axkov.moviepick.core.data.Result
import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.core.domain.models.Movie
import com.axkov.moviepick.data.entities.PopularMovieEntry
import com.axkov.moviepick.data.mappers.toDomain
import com.axkov.moviepick.data.mappers.toEntity
import com.axkov.moviepick.data.sources.local.MoviesStore
import com.axkov.moviepick.data.sources.local.PopularMoviesStore
import com.axkov.moviepick.data.sources.remote.PopularMoviesDataSource
import com.axkov.moviepick.features.home.domain.repositories.PopularMoviesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@FeatureScope
class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesDataSource: PopularMoviesDataSource,
    private val popularMoviesStore: PopularMoviesStore,
    private val moviesStore: MoviesStore
) : PopularMoviesRepository {

    override fun observeForObservable(count: Int, offset: Int): Observable<List<Movie>> {
        return popularMoviesStore.observeForObservable(count, offset)
            .map {
                it.map { popularEntryWithMovie ->
                    popularEntryWithMovie.movie.toDomain()
                }
            }
    }

    // TODO: Think about a new update strategy. Maybe it will be suitable just clear the whole database and
    // insert new entities
    override fun updatePopularMovies(page: Int): Completable {
        return popularMoviesDataSource.getPopularMovies(page)
            .flatMapCompletable { result ->
                when (result) {
                    is Result.Success -> {
                        handleSuccess(result, page)
                    }
                    is Result.Failure -> {
                        Completable.error(result.throwable)
                    }
                }
            }
    }

    private fun handleSuccess(
        result: Result.Success<List<MovieDto>>,
        page: Int
    ): Completable {
        var index = 0
        return Observable.fromIterable(result.data)
            .map { it.toEntity() }
            .flatMapMaybe { movieEntity ->
                // TODO: Should watch the correctness of the page_order field at the movies db
                // flatMap doesn't guarantee the order
    //                            .concatMapMaybe { movieEntity ->
                moviesStore.getIdOrSave(movieEntity)
                    .map {
                        PopularMovieEntry(
                            movieId = it,
                            page = page,
                            pageOrder = index++
                        )
                    }
            }
            .toList()
            .flatMapCompletable {
                popularMoviesStore.updatePopularMoviesPage(page, it)
            }
    }
}


//@FeatureScope
//class PopularMoviesRepositoryImpl @Inject constructor(
//    private val popularMoviesDataSource: PopularMoviesDataSource,
//    private val moviesDataStore: MoviesDataStore
//) : PopularMoviesRepository {
//
//    override fun observeForObservable(count: Int, offset: Int): Observable<List<Movie>> {
//        return moviesDataStore.observeForObservable(Category.POPULAR, count, offset)
//            .map { entities ->
//                entities.map { it.toDomain() }
//            }
//    }
//
//    override fun updatePopularMovies(page: Int): Completable {
//        return popularMoviesDataSource.getPopularMovies(page)
//            .flatMapCompletable { result ->
//                when (result) {
//                    is Result.Success -> {
//                        result.data.map {
//                            it.toEntity(Category.POPULAR)
//                        }.also {
//                            moviesDataStore.deleteAll()
//                            moviesDataStore.saveMovies(it)
//                        }
//
//                        Completable.complete()
//                    }
//
//                    is Result.Failure -> {
//                        Completable.error(result.throwable)
//                    }
//                }
//            }
//    }
//}