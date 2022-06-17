package com.axkov.moviepick.core.di

import dagger.Component

@Component(
    modules = [CommonUtilsModule::class],
    dependencies = [CommonUtilsDependencies::class]
)
interface CommonUtilsComponent: CommonUtilsApi {

    companion object {
        fun initAndGet(dependencies: CommonUtilsDependencies): CommonUtilsComponent {
            return DaggerCommonUtilsComponent.builder()
                .commonUtilsDependencies(dependencies)
                .build()
        }
    }
}