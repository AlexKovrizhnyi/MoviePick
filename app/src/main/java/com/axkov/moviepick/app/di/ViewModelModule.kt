package com.axkov.moviepick.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axkov.moviepick.core.di.ViewModelKey
import com.axkov.moviepick.core.ui.ViewModelFactory
import com.axkov.moviepick.features.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(HomeViewModel::class)]
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}