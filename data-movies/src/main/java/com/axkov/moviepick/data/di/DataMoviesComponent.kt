package com.axkov.moviepick.data.di

import com.axkov.moviepick.core.di.annotations.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [DataMoviesModule::class],
    dependencies = [DataMoviesFeatureDependencies::class]
)
interface DataMoviesComponent: DataMoviesFeatureApi {

    companion object {

        fun initAndGet(dependencies: DataMoviesFeatureDependencies): DataMoviesComponent {
            return DaggerDataMoviesComponent.builder()
                .dataMoviesFeatureDependencies(dependencies)
                .build()
        }

    }
}