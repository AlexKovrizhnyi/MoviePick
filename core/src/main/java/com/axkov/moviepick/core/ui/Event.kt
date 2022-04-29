package com.axkov.moviepick.core.ui

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Single live event wrapper
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

//abstract class SingleEvent<T>(
//    private val argument: T,
//    protected open val isConsumed: AtomicBoolean = AtomicBoolean(false)
//) {
//
//    private fun isConsumed(setAsConsumed: Boolean = false) = isConsumed.getAndSet(setAsConsumed)
//
//    fun consume(action: (T) -> Unit) {
//        if (!isConsumed(true)) {
//            action.invoke(argument)
//        }
//    }
//
//    fun resend() {
//        isConsumed.set(false)
//    }
//}