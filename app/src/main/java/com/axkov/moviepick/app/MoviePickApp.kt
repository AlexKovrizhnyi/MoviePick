package com.axkov.moviepick.app

import android.app.Application
import com.axkov.moviepick.app.di.FeatureInjection
import com.axkov.moviepick.core.App
import timber.log.Timber

class MoviePickApp : Application(), App {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        FeatureInjection.injectDependencies(applicationContext)
    }
}