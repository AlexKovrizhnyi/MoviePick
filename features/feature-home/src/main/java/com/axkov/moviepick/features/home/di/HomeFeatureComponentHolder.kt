package com.axkov.moviepick.features.home.di

import com.axkov.moviepick.core.di.module_injector.ComponentHolder
import com.axkov.moviepick.core.di.module_injector.ComponentHolderDelegate

object HomeFeatureComponentHolder : ComponentHolder<HomeFeatureApi, HomeFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<
            HomeFeatureApi,
            HomeFeatureDependencies,
            HomeFeatureComponent> { dependencies: HomeFeatureDependencies ->
        HomeFeatureComponent.initAndGet(dependencies)
    }

    override var dependencyProvider: (() -> HomeFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    internal fun getComponent(): HomeFeatureComponent = componentHolderDelegate.getComponentImpl()

    override fun get(): HomeFeatureApi = componentHolderDelegate.get()
}