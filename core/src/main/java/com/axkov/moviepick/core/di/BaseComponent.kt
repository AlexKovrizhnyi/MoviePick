package com.axkov.moviepick.core.di

import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface BaseComponent {

}

interface BaseComponentProvider {
    fun provideBaseComponent(): BaseComponent
}