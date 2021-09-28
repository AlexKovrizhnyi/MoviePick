package com.axkov.moviepick.core.utils

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class AndroidResourceProvider @Inject constructor(
    private val context: Context
): ResourceProvider {

    override fun getString(@StringRes id: Int) = context.getString(id)
}