package com.axkov.moviepick.app

import android.app.Application
import android.content.Context
import com.axkov.moviepick.app.di.AppComponent
import com.axkov.moviepick.app.di.DaggerAppComponent
import com.axkov.moviepick.features.home.di.HomeDepsStore
import timber.log.Timber

class MoviePickApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        HomeDepsStore.dependencies = appComponent

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MoviePickApp -> appComponent
        else -> this.applicationContext.appComponent
    }