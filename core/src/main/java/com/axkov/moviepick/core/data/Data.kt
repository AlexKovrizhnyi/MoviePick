package com.axkov.moviepick.core.data

sealed interface Data<out T> {
    data class Success<R>(val content: R): Data<R>

    data class Failure(val error: Throwable) : Data<Nothing>

    object Loading: Data<Nothing>
}