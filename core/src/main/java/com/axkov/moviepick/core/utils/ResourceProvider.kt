package com.axkov.moviepick.core.utils

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int): String
}