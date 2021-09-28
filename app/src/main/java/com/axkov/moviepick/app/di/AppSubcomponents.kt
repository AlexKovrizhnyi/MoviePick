package com.axkov.moviepick.app.di

import com.axkov.moviepick.features.home.di.HomeScreenComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeScreenComponent::class
    ]
)
class AppSubcomponents {
}