package com.axkov.moviepick.core.di

import com.axkov.moviepick.core.utils.AndroidResourceProvider
import com.axkov.moviepick.core.utils.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface CommonUtilsModule {

    @Binds
    @Reusable
    fun bindResourceProvider(provider: AndroidResourceProvider): ResourceProvider
}