package com.axkov.moviepick.features.home.domain.repositories

import com.axkov.moviepick.domain.models.Movie
import io.reactivex.rxjava3.core.Single

internal interface TrendingRepository {

    fun getTrendingWeek(): Single<List<Movie>>
}