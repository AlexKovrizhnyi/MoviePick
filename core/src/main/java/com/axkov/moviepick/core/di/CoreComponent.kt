package com.axkov.moviepick.core.di

import android.content.Context
import com.axkov.moviepick.api.TrendingService
import com.axkov.moviepick.core.utils.ResourceProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent {

    fun getResource(): ResourceProvider
    fun getApi(): TrendingService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): CoreComponent
    }
}