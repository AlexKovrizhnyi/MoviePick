package com.axkov.moviepick.database.di

import android.content.Context
import com.axkov.moviepick.core.di.module_injector.FeatureDependencies

interface AppDbDependencies: FeatureDependencies {
    val context: Context
}