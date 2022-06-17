package com.axkov.moviepick.core.di

import com.axkov.moviepick.core.di.module_injector.ComponentHolder
import com.axkov.moviepick.core.di.module_injector.ComponentHolderDelegate

object CommonUtilsComponentHolder : ComponentHolder<CommonUtilsApi, CommonUtilsDependencies> {

    private val componentHolderDelegate = ComponentHolderDelegate<
            CommonUtilsApi,
            CommonUtilsDependencies,
            CommonUtilsComponent> { dependencies -> CommonUtilsComponent.initAndGet(dependencies) }

    override var dependencyProvider: (() -> CommonUtilsDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): CommonUtilsApi = componentHolderDelegate.get()
}