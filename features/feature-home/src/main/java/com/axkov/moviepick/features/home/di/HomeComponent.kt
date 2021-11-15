package com.axkov.moviepick.features.home.di

import com.axkov.moviepick.api.TmdbApi
import com.axkov.moviepick.core.di.DependenciesProvider
import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.core.utils.ResourceProvider
import com.axkov.moviepick.features.home.ui.HomeFragment
import com.axkov.moviepick.features.home.ui.HomeViewModel
import dagger.Component
import kotlin.properties.Delegates.notNull

@FeatureScope
@Component(
    modules = [HomeModule::class],
    dependencies = [HomeDependencies::class]
)
internal interface HomeComponent {

    val homeViewModel: HomeViewModel

    fun inject(fragment: HomeFragment)
}

interface HomeDependencies {

    val resourceProvider: ResourceProvider

    val tmdbApi: TmdbApi
}

object HomeDepsStore : DependenciesProvider<HomeDependencies> {
    override var dependencies: HomeDependencies by notNull()
}