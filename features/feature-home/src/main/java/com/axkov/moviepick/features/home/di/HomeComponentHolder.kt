package com.axkov.moviepick.features.home.di

import androidx.lifecycle.ViewModel
import timber.log.Timber

internal class HomeComponentHolder : ViewModel() {

    val homeComponent: HomeComponent = DaggerHomeComponent.builder()
        .homeDependencies(HomeDepsStore.dependencies)
        .build()

    init {
        Timber.d("HomeComponentHolder is created")
    }
}