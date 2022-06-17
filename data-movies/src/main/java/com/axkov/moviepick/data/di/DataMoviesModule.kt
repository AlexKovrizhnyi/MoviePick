package com.axkov.moviepick.data.di

import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.data.datasources.local.MoviesStore
import com.axkov.moviepick.data.datasources.local.PopularMoviesStore
import com.axkov.moviepick.data.datasources.remote.MovieListDataSource
import com.axkov.moviepick.data.repositories.MovieListRepository
import com.axkov.moviepick.data.repositories.MoviesPagingRepository
import com.axkov.moviepick.data.repositories.PopularMoviesRepository
import com.axkov.moviepick.data.repositories.impl.MovieListRepositoryImpl
import com.axkov.moviepick.data.repositories.impl.MoviesPagingRepositoryImpl
import com.axkov.moviepick.data.repositories.impl.PopularMoviesRepositoryImpl
import com.axkov.moviepick.tmdb.enums.MovieList
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
internal interface DataMoviesModule {

    @Binds
    fun bindMoviePagingRepository(repositoryImpl: MoviesPagingRepositoryImpl): MoviesPagingRepository

    @Binds
    fun bindPopularMoviesRepository(popularMoviesRepository: PopularMoviesRepositoryImpl): PopularMoviesRepository

//    @Binds
//    fun bindMovieListRepository(movieListRepository: MovieListRepositoryImpl): MovieListRepository

    companion object {

        @[FeatureScope Provides Named("POPULAR")]
        fun providePopularMovieListRepository(
            movieListDataSource: MovieListDataSource,
            popularMoviesStore: PopularMoviesStore,
            moviesStore: MoviesStore
        ): MovieListRepository = MovieListRepositoryImpl(
            movieListType = MovieList.POPULAR,
            movieListDataSource = movieListDataSource,
            movieListStore = popularMoviesStore,
            moviesStore = moviesStore
        )

//        @[FeatureScope Provides Named("TOP_RATED")]
//        fun provideTopRatedMovieListRepository(
//            movieListDataSource: MovieListDataSource,
//            topRatedMoviesStore: TopRatedMoviesStore,
//            moviesStore: MoviesStore
//        ): MovieListRepository = MovieListRepositoryImpl(
//            movieListType = MovieList.TOP_RATED,
//            movieListDataSource = movieListDataSource,
//            movieListStore = topRatedMoviesStore,
//            moviesStore = moviesStore
//        )
//
//        @[FeatureScope Provides Named("UPCOMING")]
//        fun provideUpcomingMovieListRepository(
//            movieListDataSource: MovieListDataSource,
//            upcomingMovies: UpcomingMoviesStore,
//            moviesStore: com.axkov.moviepick.data.datasources.MoviesStore
//        ): MovieListRepository = MovieListRepositoryImpl(
//            movieListType = MovieList.TOP_RATED,
//            movieListDataSource = movieListDataSource,
//            movieListStore = upcomingMovies,
//            moviesStore = moviesStore
//        )
    }
}