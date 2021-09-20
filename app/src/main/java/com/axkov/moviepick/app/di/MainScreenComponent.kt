package com.axkov.moviepick.app.di

import android.content.Context
import com.axkov.moviepick.app.appComponent
import com.axkov.moviepick.app.di.annotations.MainScreenScope
import com.axkov.moviepick.app.di.modules.MainScreenModule
import com.axkov.moviepick.app.di.modules.ViewModelModule
import com.axkov.moviepick.app.ui.base.ViewModelFactory
import com.axkov.moviepick.app.utils.ResourceProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    MainScreenModule::class,
    ViewModelModule::class
])
@MainScreenScope
interface MainScreenComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun resources(resourceProvider: ResourceProvider): Builder

        fun build(): MainScreenComponent
    }

    companion object {
        fun create(context: Context): MainScreenComponent = DaggerMainScreenComponent
            .builder()
            .resources(context.appComponent.getResources())
            .build()
    }
}

