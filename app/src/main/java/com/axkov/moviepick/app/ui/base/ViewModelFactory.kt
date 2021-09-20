package com.axkov.moviepick.app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")

        return viewModelProvider.get() as T

//        var viewModelProvider: Provider<out ViewModel>? = creators[modelClass]
//
//        if (viewModelProvider == null) {
//            for ((key, value) in creators) {
//                if (modelClass.isAssignableFrom(key)) {
//                    viewModelProvider = value
//                    break
//                }
//            }
//        }
//
//        if (viewModelProvider == null) {
//            throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
//        }
//
//        try {
//            return viewModelProvider.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
    }
}