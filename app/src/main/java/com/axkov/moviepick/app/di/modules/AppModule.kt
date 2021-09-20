package com.axkov.moviepick.app.di.modules

import com.axkov.moviepick.app.utils.AndroidResourceProvider
import com.axkov.moviepick.app.utils.ResourceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    abstract fun bindResourceProvider(provider: AndroidResourceProvider): ResourceProvider
}