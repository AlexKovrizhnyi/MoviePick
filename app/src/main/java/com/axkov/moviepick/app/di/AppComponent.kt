package com.axkov.moviepick.app.di

import android.content.Context
import com.axkov.moviepick.api.TrendingService
import com.axkov.moviepick.core.di.CoreModule
import com.axkov.moviepick.core.utils.ResourceProvider
import com.axkov.moviepick.features.home.di.HomeScreenComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        AppSubcomponents::class,
        CoreModule::class
    ]
)
@AppScope
interface AppComponent {

    fun getResource(): ResourceProvider
    fun getApi(): TrendingService

    fun plusHomeScreenComponent(): HomeScreenComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }
}