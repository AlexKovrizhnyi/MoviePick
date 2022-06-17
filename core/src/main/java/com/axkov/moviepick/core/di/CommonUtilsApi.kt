package com.axkov.moviepick.core.di

import com.axkov.moviepick.core.di.module_injector.FeatureApi
import com.axkov.moviepick.core.utils.ResourceProvider

interface CommonUtilsApi: FeatureApi {
    val resourceProvider: ResourceProvider
}