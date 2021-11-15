package com.axkov.moviepick.app.di

import android.content.Context
import com.axkov.moviepick.core.di.annotations.AppScope
import com.axkov.moviepick.features.home.di.HomeDependencies
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent : HomeDependencies {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context
        ): AppComponent
    }
}