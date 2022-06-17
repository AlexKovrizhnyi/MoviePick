package com.axkov.moviepick.data.di

import com.axkov.moviepick.core.di.module_injector.ComponentHolder
import com.axkov.moviepick.core.di.module_injector.ComponentHolderDelegate

object DataMoviesComponentHolder :
    ComponentHolder<DataMoviesFeatureApi, DataMoviesFeatureDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<
            DataMoviesFeatureApi, DataMoviesFeatureDependencies, DataMoviesComponent> { dependencies ->
        DataMoviesComponent.initAndGet(dependencies)
    }

    override var dependencyProvider: (() -> DataMoviesFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    internal fun getComponent(): DataMoviesComponent = componentHolderDelegate.getComponentImpl()

    override fun get(): DataMoviesFeatureApi = componentHolderDelegate.get()
}