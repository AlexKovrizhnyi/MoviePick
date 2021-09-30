package com.axkov.moviepick.app

import android.app.Application
import android.content.Context
import com.axkov.moviepick.app.di.AppComponent
import com.axkov.moviepick.app.di.DaggerAppComponent
import com.axkov.moviepick.core.di.CoreComponent
import com.axkov.moviepick.core.di.DaggerCoreComponent
import com.axkov.moviepick.features.home.di.HomeScreenComponent
import com.axkov.moviepick.features.home.di.HomeScreenComponentBuilderProvider

class MoviePickApp: Application(), HomeScreenComponentBuilderProvider {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .appContext(applicationContext)
            .build()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .coreComponent(coreComponent)
            .build()
    }


    override fun onCreate() {
        super.onCreate()

    }

//    override fun getHomeScreenComponent(): HomeScreenComponent {
//        return appComponent.plusHomeScreenComponent().build()
//    }

    override fun get(): HomeScreenComponent.Builder {
        return appComponent.plusHomeScreenComponent()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MoviePickApp -> appComponent
        else -> this.applicationContext.appComponent
    }

val Context.coreComponent: AppComponent
    get() = when (this) {
        is MoviePickApp -> appComponent
        else -> this.applicationContext.coreComponent
    }