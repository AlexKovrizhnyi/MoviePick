package com.axkov.moviepick.tmdb.di

import com.axkov.moviepick.core.di.annotations.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [NetworkModule::class],
    dependencies = [NetworkTmdbDependencies::class]
)
interface NetworkTmdbComponent : NetworkTmdbApi {

    companion object {
        fun initAndGet(dependencies: NetworkTmdbDependencies): NetworkTmdbComponent {
            return DaggerNetworkTmdbComponent.builder()
                .networkTmdbDependencies(dependencies)
                .build()
        }
    }
}