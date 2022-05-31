package com.axkov.moviepick.features.home.domain.usecases

import com.axkov.moviepick.core.domain.UseCase
import com.axkov.moviepick.features.home.domain.repositories.PopularMoviesRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UpdatePopularMovies @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) : UseCase<UpdatePopularMovies.Params, Completable>() {

    override fun doWork(params: Params): Completable {
        val page = when {
            params.page >= 0 -> params.page
            params.page == Page.NEXT_PAGE -> {
                val lastPage: Int? = null // TODO: getLastPage() from the db
                if (lastPage != null) lastPage + 1 else 1
            }
            else -> 1
        }

        // TODO: Implement resetOnSave
        return popularMoviesRepository.updatePopularMovies(page, resetOnSave = false)
    }

    data class Params(val page: Int, val forceRefresh: Boolean = false)

    object Page {
        const val NEXT_PAGE = -1
        const val REFRESH = -2
    }
}