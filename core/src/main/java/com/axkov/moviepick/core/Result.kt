package com.axkov.moviepick.core

sealed class Result<T> {
    open fun get(): T? = null

    fun getOrThrow(): T = when (this) {
        is Success -> get()
        is Failure -> throw throwable
    }

    data class Success<T>(val data: T): Result<T>() {
        override fun get(): T = data
    }

    data class Failure<T>(val throwable: Throwable): Result<T>()
}



//class Result<out T>(val status: Status, val data: T?, val message: String?) {
//
//    enum class Status {
//        SUCCESS,
//        ERROR,
//        LOADING
//    }
//
//    companion object {
//        fun <T> success(data: T): Result<T> = Result(Status.SUCCESS, data, null)
//
//        fun <T> error(message: String, data: T? = null): Result<T> = Result(Status.ERROR, data, message)
//
//        fun <T> loading(data: T? = null): Result<T> = Result(Status.LOADING, data, null)
//    }
//}