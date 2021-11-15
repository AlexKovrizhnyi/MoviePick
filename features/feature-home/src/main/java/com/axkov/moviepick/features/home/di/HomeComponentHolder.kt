package com.axkov.moviepick.features.home.di

import androidx.lifecycle.ViewModel

internal class HomeComponentHolder : ViewModel() {

    val homeComponent: HomeComponent = DaggerHomeComponent.builder()
        .homeDependencies(HomeDepsStore.dependencies)
        .build()
}