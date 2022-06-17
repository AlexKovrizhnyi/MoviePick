package com.axkov.moviepick.data.datasources.remote

import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.core.models.Movie
import com.axkov.moviepick.data.mappers.toDomain
import com.axkov.moviepick.tmdb.TmdbApi
import com.axkov.moviepick.tmdb.enums.MediaType
import com.axkov.moviepick.tmdb.enums.TimeWindow
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@FeatureScope
class TrendingMoviesDataSource @Inject constructor(
    private val api: TmdbApi
) {

    fun getTrendingWeek(page: Int): Single<List<Movie>> {
        return api.moviesService.fetchTrendingWeek(MediaType.MOVIE, TimeWindow.WEEK, page)
            .subscribeOn(Schedulers.io())
            .map {
                it.results.map { movieDto -> movieDto.toDomain() }
            }
    }

}