package com.axkov.moviepick.features.home.data.sources.remote

import com.axkov.moviepick.api.TmdbApi
import com.axkov.moviepick.core.utils.mappers.toMovie
import com.axkov.moviepick.domain.models.Movie
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

internal class TrendingDataSource @Inject constructor(
    private val api: TmdbApi
) {

    fun getTrendingWeek(): Single<List<Movie>> {
        return api.trending.getTrendingWeek()
            .subscribeOn(Schedulers.io())
            .map {
                it.results.map { movieDto -> movieDto.toMovie() }
            }
    }
}