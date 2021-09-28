package com.axkov.moviepick.app

import android.app.Application
import android.content.Context
import com.axkov.moviepick.app.di.AppComponent
import com.axkov.moviepick.app.di.DaggerAppComponent
import com.axkov.moviepick.features.home.di.HomeScreenComponent
import com.axkov.moviepick.features.home.di.HomeScreenComponentProvider

class MoviePickApp: Application(), HomeScreenComponentProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appContext(applicationContext)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun getHomeScreenComponent(): HomeScreenComponent {
        return appComponent.plusHomeScreenComponent().build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MoviePickApp -> appComponent
        else -> this.applicationContext.appComponent
    }