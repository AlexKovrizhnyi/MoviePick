package com.axkov.moviepick.app.di

import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
    ]
)
interface AppModule