package com.axkov.moviepick.features.home.domain.usecases

import com.axkov.moviepick.features.home.domain.repositories.TrendingRepository
import javax.inject.Inject


internal class GetTrending @Inject constructor(
    private val trendingRepository: TrendingRepository
) {

    operator fun invoke() = trendingRepository.getTrendingWeek()
}