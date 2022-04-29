package com.axkov.moviepick.data.repositories

import com.axkov.moviepick.core.data.Result
import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.data.entities.PopularMovieEntry
import com.axkov.moviepick.data.mappers.toDomain
import com.axkov.moviepick.data.mappers.toEntity
import com.axkov.moviepick.data.sources.local.MoviesStore
import com.axkov.moviepick.data.sources.local.PopularMoviesStore
import com.axkov.moviepick.data.sources.remote.PopularMoviesDataSource
import com.axkov.moviepick.domain.models.Movie
import com.axkov.moviepick.domain.repositories.PopularMoviesRepository
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

    override fun updatePopularMovies(page: Int): Completable {
        return popularMoviesDataSource.getPopularMovies(page)
            .flatMapCompletable { result ->
                when (result) {
                    is Result.Success -> {
                        var index = 0
                        Observable.fromIterable(result.data)
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


//                        result.data.map { it.toEntity() }
//                            .let { entities ->
//                                val entries = entities.mapIndexed { index, entity ->
//                                    val movieId = moviesStore.getIdOrSave(entity)
////                                        .subscribeOn(Schedulers.io())
//                                        .blockingGet()
//                                    PopularMovieEntry(
//                                        movieId = movieId,
//                                        page = page,
//                                        pageOrder = index
//                                    )
//                                }
//                                popularMoviesStore.updatePopularMoviesPage(page, entries)
//                            }
                    }
                    is Result.Failure -> {
                        Completable.error(result.throwable)
                    }
                }
            }
    }
}