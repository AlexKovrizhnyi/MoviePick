package com.axkov.moviepick.core.di

interface DependenciesProvider<T> {
    val dependencies: T
}