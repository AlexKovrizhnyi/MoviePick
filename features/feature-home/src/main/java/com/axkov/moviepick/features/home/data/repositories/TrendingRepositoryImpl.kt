package com.axkov.moviepick.features.home.data.repositories

import com.axkov.moviepick.domain.models.Movie
import com.axkov.moviepick.features.home.data.sources.remote.TrendingDataSource
import com.axkov.moviepick.features.home.domain.repositories.TrendingRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val dataSource: TrendingDataSource,
): TrendingRepository {

    // TODO: Repository fetches the data from data sources and returns Result object

    override fun getTrendingWeek(): Single<List<Movie>> {
        return dataSource.getTrendingWeek()
    }

}