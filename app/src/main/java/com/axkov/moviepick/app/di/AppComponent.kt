package com.axkov.moviepick.app.di

import com.axkov.moviepick.core.di.CoreComponent
import com.axkov.moviepick.features.home.di.HomeScreenComponent
import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class,
    ],
    dependencies = [CoreComponent::class]
)
interface AppComponent {

    fun plusHomeScreenComponent(): HomeScreenComponent.Builder
}