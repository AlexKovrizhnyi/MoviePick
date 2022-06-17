package com.axkov.moviepick.database.di

import com.axkov.moviepick.core.di.module_injector.ComponentHolder
import com.axkov.moviepick.core.di.module_injector.ComponentHolderDelegate

object AppDbComponentHolder : ComponentHolder<AppDbApi, AppDbDependencies> {

    private val componentHolderDelegate =
        ComponentHolderDelegate<AppDbApi, AppDbDependencies, AppDbComponent> { dependencies ->
            AppDbComponent.initAndGet(dependencies)
        }

    override var dependencyProvider: (() -> AppDbDependencies)? by componentHolderDelegate::dependencyProvider

    internal fun getComponent(): AppDbComponent = componentHolderDelegate.getComponentImpl()

    override fun get(): AppDbApi = componentHolderDelegate.get()
}