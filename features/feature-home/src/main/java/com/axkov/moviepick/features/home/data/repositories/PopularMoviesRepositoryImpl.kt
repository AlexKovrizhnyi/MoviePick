package com.axkov.moviepick.features.home.data.repositories

import com.axkov.moviepick.api.models.MovieDto
import com.axkov.moviepick.core.data.Data
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
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

@FeatureScope
class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMoviesDataSource: PopularMoviesDataSource,
    private val popularMoviesStore: PopularMoviesStore,
    private val moviesStore: MoviesStore
) : PopularMoviesRepository {

    private val dataStream = PublishSubject.create<Data<List<Movie>>>()

    private val observingParams: ObservingParams = ObservingParams(0, 0)

    data class ObservingParams(
        var count: Int,
        var offset: Int
    ) {
        fun setParams(count: Int, offset: Int) {
            this.count = count
            this.offset = offset
        }
    }

    override fun observeForObservable(count: Int, offset: Int): Observable<Data<List<Movie>>> {
//        getMoviesFromStorage(count, offset)
//            .subscribe(dataStream)

        getMoviesFromStorage(count, offset).toObservable()
            .subscribe { dataStream.onNext(it) }

        // TODO: Maybe find better implementation of setting count and offset parameters
        observingParams.setParams(count, offset)

        return dataStream.hide()
    }

    // Room returns an observable that updates every time the database has changed
//    private fun getMoviesFromStorage(count: Int, offset: Int): Observable<DataS<List<Movie>>> {
//        return popularMoviesStore.observeForObservable(count, offset)
//            .subscribeOn(Schedulers.io())
//            .map {
//                DataS.Success(it.map { popularEntryWithMovie ->
//                    popularEntryWithMovie.movie.toDomain()
//                })
//            }
//    }

    // Room returns a single value with a wrapped list only once
    private fun getMoviesFromStorage(count: Int, offset: Int): Single<Data<List<Movie>>> {
        return popularMoviesStore.getEntries(count, offset)
            .subscribeOn(Schedulers.io())
            .map {
                Data.Success(it.map { popularEntryWithMovie ->
                    popularEntryWithMovie.movie.toDomain()
                })
            }
    }

    // TODO: Extract pageSize as a constant
    override fun updatePopularMovies(page: Int, resetOnSave: Boolean): Completable {
        return popularMoviesDataSource.getPopularMovies(page, 10)
            .doOnSubscribe { dataStream.onNext(Data.Loading) }
            .flatMapCompletable { result ->
                when (result) {
                    is Result.Success -> {
                        handleSuccess(result, page, resetOnSave)
                            .subscribeOn(Schedulers.io())
                            .andThen(
                                getMoviesFromStorage(observingParams.count, observingParams.offset)
                                    .flatMapCompletable {
                                        dataStream.onNext(it)
                                        Completable.complete()
                                    }
                            )
                    }
                    is Result.Failure -> {
                        dataStream.onNext(Data.Failure(result.throwable))
                        Completable.complete()
                    }
                }
            }
    }

    private fun handleSuccess(
        result: Result.Success<List<MovieDto>>,
        page: Int,
        resetOnSave: Boolean
    ): Completable {

        return Observable.just(result.data)
            .map { listDto -> listDto.map { it.toEntity() } }
            .flatMap {
                moviesStore.saveMovies(it)
                    .andThen(Observable.just(it))
            }
            .map { entities ->
                entities.mapIndexed { index, entity ->
                    entity to index
                }
            }
            .flatMapIterable { pairs -> pairs }
            .flatMapMaybe { pair ->
                moviesStore.getIdOrSave(pair.first)
                    .map {
                        Timber.d("entity = ${pair.first.title} thread = ${Thread.currentThread().name}")
                        PopularMovieEntry(
                            movieId = it,
                            page = page,
                            pageOrder = pair.second
                        )
                    }
                    .subscribeOn(Schedulers.io())
            }
            .toList()
            .flatMapCompletable {
                popularMoviesStore.savePopularMoviesPage(page, it)
            }
    }
}