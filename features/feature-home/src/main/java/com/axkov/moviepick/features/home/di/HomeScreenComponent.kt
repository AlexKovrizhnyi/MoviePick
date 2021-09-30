package com.axkov.moviepick.features.home.di

import com.axkov.moviepick.core.ui.ViewModelFactory
import dagger.Subcomponent
import javax.inject.Provider
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class HomeScreenScope

@Subcomponent(
    modules = [
        HomeScreenModule::class,
    ]
)
@HomeScreenScope
interface HomeScreenComponent {

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {
//        fun api(trendingService: TrendingService): Builder

        fun build(): HomeScreenComponent
    }
}

interface HomeScreenComponentBuilderProvider: Provider<HomeScreenComponent.Builder>