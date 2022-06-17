package com.axkov.moviepick.tmdb.di

import com.axkov.moviepick.core.di.module_injector.ComponentHolder
import com.axkov.moviepick.core.di.module_injector.ComponentHolderDelegate

object NetworkTmdbComponentHolder: ComponentHolder<NetworkTmdbApi, NetworkTmdbDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<
            NetworkTmdbApi,
            NetworkTmdbDependencies,
            NetworkTmdbComponent> { dependencies ->
        NetworkTmdbComponent.initAndGet(dependencies)
    }

    override var dependencyProvider: (() -> NetworkTmdbDependencies)? by componentHolderDelegate::dependencyProvider

    internal fun getComponent(): NetworkTmdbComponent = componentHolderDelegate.getComponentImpl()

    override fun get(): NetworkTmdbApi = componentHolderDelegate.get()
}