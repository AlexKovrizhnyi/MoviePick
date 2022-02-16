package com.axkov.moviepick.features.home.di

import android.content.Context
import com.axkov.moviepick.api.TmdbApi
import com.axkov.moviepick.core.di.DependenciesProvider
import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.core.utils.ResourceProvider
import com.axkov.moviepick.data.DatabaseModule
import com.axkov.moviepick.features.home.ui.category.CategoryViewModel
import com.axkov.moviepick.features.home.ui.home.HomeFragment
import com.axkov.moviepick.features.home.ui.home.HomeViewModel
import dagger.Component
import kotlin.properties.Delegates.notNull

@FeatureScope
@Component( // TODO I think that it's a bad place for DatabaseModule, maybe will be better if I move it to AppComponent
    modules = [HomeModule::class, DatabaseModule::class],
    dependencies = [HomeDependencies::class]
)
internal interface HomeComponent {

    val homeViewModel: HomeViewModel

    val categoryViewModel: CategoryViewModel

    fun inject(fragment: HomeFragment)
}

interface HomeDependencies {

    val resourceProvider: ResourceProvider

    val tmdbApi: TmdbApi

    val context: Context
}

object HomeDepsStore : DependenciesProvider<HomeDependencies> {
    override var dependencies: HomeDependencies by notNull()
}