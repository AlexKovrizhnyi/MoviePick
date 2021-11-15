package com.axkov.moviepick.core.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> Fragment.viewModel(
    crossinline provider: () -> VM
) = viewModels<VM> {
    @Suppress("UNCHECKED_CAST")
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = provider() as T
    }
}

inline fun <reified VM : ViewModel> Fragment.activityViewModel(
    crossinline provider: () -> VM
) = activityViewModels<VM> {
    @Suppress("UNCHECKED_CAST")
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = provider() as T
    }
}