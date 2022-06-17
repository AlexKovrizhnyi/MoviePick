package com.axkov.moviepick.database.di

import com.axkov.moviepick.core.di.annotations.AppScope
import dagger.Component

@AppScope
@Component(
    modules = [DatabaseModule::class],
    dependencies = [AppDbDependencies::class]
)
abstract class AppDbComponent : AppDbApi {

    companion object {

        fun initAndGet(dependencies: AppDbDependencies): AppDbComponent {
            return DaggerAppDbComponent.builder()
                .appDbDependencies(dependencies)
                .build()
        }
    }
}