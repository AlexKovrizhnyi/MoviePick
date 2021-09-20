package com.axkov.moviepick.app.utils

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int): String
}