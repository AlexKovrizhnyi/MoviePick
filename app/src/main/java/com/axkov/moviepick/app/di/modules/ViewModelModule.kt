package com.axkov.moviepick.app.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axkov.moviepick.app.di.annotations.ViewModelKey
import com.axkov.moviepick.app.ui.base.ViewModelFactory
import com.axkov.moviepick.app.ui.main.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @[IntoMap ViewModelKey(MainScreenViewModel::class)]
    abstract fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}