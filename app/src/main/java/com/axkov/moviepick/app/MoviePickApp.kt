package com.axkov.moviepick.app

import android.app.Application
import android.content.Context
import com.axkov.moviepick.app.di.AppComponent
import com.axkov.moviepick.app.di.DaggerAppComponent

class MoviePickApp: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appContext(applicationContext)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MoviePickApp -> appComponent
        else -> this.applicationContext.appComponent
    }