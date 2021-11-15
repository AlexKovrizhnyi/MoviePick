package com.axkov.moviepick.app.di

import com.axkov.moviepick.core.di.CoreModule
import com.axkov.moviepick.core.di.NetworkModule
import dagger.Module

@Module(
    includes = [
        CoreModule::class,
        NetworkModule::class
    ]
)
interface AppModule