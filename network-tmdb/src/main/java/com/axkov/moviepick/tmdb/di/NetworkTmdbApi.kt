package com.axkov.moviepick.tmdb.di

import com.axkov.moviepick.core.di.module_injector.FeatureApi
import com.axkov.moviepick.tmdb.TmdbApi

interface NetworkTmdbApi: FeatureApi {

    val tmdbApi: TmdbApi
}