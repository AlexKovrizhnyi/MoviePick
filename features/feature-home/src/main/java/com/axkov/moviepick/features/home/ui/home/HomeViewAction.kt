package com.axkov.moviepick.features.home.ui.home

import com.axkov.moviepick.core.domain.enums.MoviesCategory

internal sealed class HomeViewAction {
    object Refresh : HomeViewAction()
    data class OnMovieItemClick(val movieId: Int) : HomeViewAction()
    data class OnShowMoreCategoryClick(val category: MoviesCategory) : HomeViewAction()
}