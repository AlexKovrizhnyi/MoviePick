package com.axkov.moviepick.features.home.di

import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.features.home.ui.category.CategoryViewModel
import com.axkov.moviepick.features.home.ui.home.HomeViewModel
import dagger.Component

@FeatureScope
@Component(
    modules = [HomeModule::class],
    dependencies = [HomeFeatureDependencies::class]
)
internal interface HomeFeatureComponent : HomeFeatureApi {

    val homeViewModel: HomeViewModel

    val categoryViewModelFactory: CategoryViewModel.Factory

    companion object {
        fun initAndGet(dependencies: HomeFeatureDependencies): HomeFeatureComponent {
            return DaggerHomeFeatureComponent.builder()
                .homeFeatureDependencies(dependencies)
                .build()
        }
    }
}