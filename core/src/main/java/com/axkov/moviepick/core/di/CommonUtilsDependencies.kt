package com.axkov.moviepick.core.di

import android.content.Context
import com.axkov.moviepick.core.di.module_injector.FeatureDependencies

interface CommonUtilsDependencies: FeatureDependencies {
    val context: Context
}