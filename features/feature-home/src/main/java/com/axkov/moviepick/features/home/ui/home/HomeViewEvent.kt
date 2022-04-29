package com.axkov.moviepick.features.home.ui.home

import com.axkov.moviepick.domain.models.MoviesCategory

internal sealed class HomeViewEvent {
    object NavigateToDetails : HomeViewEvent()
    data class NavigateToCategory(val category: MoviesCategory) : HomeViewEvent()
    data class ShowSnackBar(val message: String) : HomeViewEvent()
    data class ShowToast(val message: String) : HomeViewEvent()
}