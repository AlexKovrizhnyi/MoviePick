package com.axkov.moviepick.core.di.module_injector

interface FeatureApi

interface FeatureDependencies {
    val dependenciesHolder: BaseDependencyHolder<out FeatureDependencies>
}

// ComponentHolder with lazy initialization
interface ComponentHolder<A: FeatureApi, D: FeatureDependencies> {

    var dependencyProvider: (() -> D)?

    fun get(): A
}