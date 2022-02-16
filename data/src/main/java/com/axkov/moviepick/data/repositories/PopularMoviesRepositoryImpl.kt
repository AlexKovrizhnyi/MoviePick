package com.axkov.moviepick.data.repositories

import com.axkov.moviepick.core.data.Result
import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.data.sources.local.MoviesDataStore
import com.axkov.moviepick.data.sources.remote.PopularMoviesDataSource
import com.axkov.moviepick.data.toDomain
import com.axkov.moviepick.data.toEntity
import com.axkov.moviepick.domain.models.Category
import com.axkov.moviepick.domain.models.Movie
import com.axkov.moviepick.domain.repositories.PopularMoviesRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@FeatureScope
class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesDataSource: PopularMoviesDataSource,
    private val moviesDataStore: MoviesDataStore
) : PopularMoviesRepository {

    override fun observeForObservable(count: Int, offset: Int): Observable<List<Movie>> {
        return moviesDataStore.observeForObservable(Category.POPULAR, count, offset)
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override fun updatePopularMovies(page: Int): Completable {
        return popularMoviesDataSource.getPopularMovies(page)
            .flatMapCompletable { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.map {
                            it.toEntity(Category.POPULAR)
                        }.also {
                            moviesDataStore.deleteAll()
                            moviesDataStore.saveMovies(it)
                        }

                        Completable.complete()
                    }

                    is Result.Failure -> {
                        Completable.error(result.throwable)
                    }
                }
            }
    }
}