package com.axkov.moviepick.features.home.ui.home

import com.axkov.moviepick.core.domain.enums.MoviesCategory
import com.axkov.moviepick.core.ui.UiMessage
import com.axkov.moviepick.features.home.ui.models.ListItem
import com.axkov.moviepick.features.home.ui.models.MovieItem

internal data class HomeViewState(
    val popular: CategoryState = CategoryState(MoviesCategory.POPULAR),
    val topRated: CategoryState = CategoryState(MoviesCategory.TOP_RATED),
    val upcoming: CategoryState = CategoryState(MoviesCategory.UPCOMING),
    val messages: List<UiMessage> = listOf()
) {

    fun updateCategory(
        category: MoviesCategory,
        items: List<MovieItem>? = null,
        loading: Boolean,
    ): HomeViewState {

        return when (category) {
            MoviesCategory.POPULAR -> {
                this.copy(
                    popular = popular.copy(
                        items = items ?: popular.items,
                        loading = loading
                    )
                )
            }
            MoviesCategory.TOP_RATED -> {
                this.copy(
                    topRated = topRated.copy(
                        items = items ?: topRated.items,
                        loading = loading
                    )
                )
            }
            MoviesCategory.UPCOMING -> {
                this.copy(
                    upcoming = upcoming.copy(
                        items = items ?: upcoming.items,
                        loading = loading
                    )
                )
            }
        }
    }
}

internal data class CategoryState(
    val category: MoviesCategory,
    val items: List<ListItem> = listOf(),
    val loading: Boolean = false
)

internal sealed interface PartState {

    data class MessageShown(val messageId: Long) : PartState

    data class MoviesLoading(val category: MoviesCategory) : PartState

    data class MoviesError(
        val category: MoviesCategory,
        val message: UiMessage
    ) : PartState

    data class MoviesLoaded(
        val category: MoviesCategory,
        val content: List<MovieItem>
    ) : PartState
}