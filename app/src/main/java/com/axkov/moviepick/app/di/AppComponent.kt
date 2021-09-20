package com.axkov.moviepick.app.di

import android.content.Context
import com.axkov.moviepick.app.di.annotations.AppScope
import com.axkov.moviepick.app.di.modules.AppModule
import com.axkov.moviepick.app.utils.ResourceProvider
import com.axkov.moviepick.core.network.api.MovieDbApiService
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

    fun getResources(): ResourceProvider
    fun getApi(): MovieDbApiService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }
}