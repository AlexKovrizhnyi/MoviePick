package com.axkov.moviepick.app.di

import android.content.Context
import com.axkov.moviepick.core.di.CommonUtilsApi
import com.axkov.moviepick.core.di.CommonUtilsComponentHolder
import com.axkov.moviepick.core.di.CommonUtilsDependencies
import com.axkov.moviepick.core.di.module_injector.*
import com.axkov.moviepick.core.utils.ResourceProvider
import com.axkov.moviepick.data.di.DataMoviesComponentHolder
import com.axkov.moviepick.data.di.DataMoviesFeatureApi
import com.axkov.moviepick.data.di.DataMoviesFeatureDependencies
import com.axkov.moviepick.data.repositories.MoviesPagingRepository
import com.axkov.moviepick.data.repositories.PopularMoviesRepository
import com.axkov.moviepick.database.daos.MoviesDao
import com.axkov.moviepick.database.daos.PopularMoviesDao
import com.axkov.moviepick.database.di.AppDbApi
import com.axkov.moviepick.database.di.AppDbComponentHolder
import com.axkov.moviepick.database.di.AppDbDependencies
import com.axkov.moviepick.database.utils.DatabaseTransactionRunner
import com.axkov.moviepick.database.utils.EntityInserter
import com.axkov.moviepick.features.home.di.HomeFeatureComponentHolder
import com.axkov.moviepick.features.home.di.HomeFeatureDependencies
import com.axkov.moviepick.tmdb.TmdbApi
import com.axkov.moviepick.tmdb.di.NetworkTmdbApi
import com.axkov.moviepick.tmdb.di.NetworkTmdbComponentHolder
import com.axkov.moviepick.tmdb.di.NetworkTmdbDependencies

internal object FeatureInjection {

    fun injectDependencies(appContext: Context) {

        CommonUtilsComponentHolder.dependencyProvider = {
            class CommonUtilsComponentDependencyHolder(
                override val block: (BaseDependencyHolder<CommonUtilsDependencies>) -> CommonUtilsDependencies
            ) : DependencyHolder0<CommonUtilsDependencies>()

            CommonUtilsComponentDependencyHolder { dependencyHolder ->
                object : CommonUtilsDependencies {
                    override val dependenciesHolder: BaseDependencyHolder<out FeatureDependencies> =
                        dependencyHolder
                    override val context: Context
                        get() = appContext
                }
            }.dependencies
        }

        AppDbComponentHolder.dependencyProvider = {
            class AppDbDependenciesHolder(
                override val block: (BaseDependencyHolder<AppDbDependencies>) -> AppDbDependencies
            ) : DependencyHolder0<AppDbDependencies>()

            AppDbDependenciesHolder { dependencyHolder ->
                object : AppDbDependencies {
                    override val context: Context = appContext
                    override val dependenciesHolder: BaseDependencyHolder<out FeatureDependencies> =
                        dependencyHolder
                }
            }.dependencies
        }

        NetworkTmdbComponentHolder.dependencyProvider = {
            class NetworkTmdbComponentDependencyHolder(
                override val block: (BaseDependencyHolder<NetworkTmdbDependencies>, CommonUtilsApi) -> NetworkTmdbDependencies
            ) : DependencyHolder1<CommonUtilsApi, NetworkTmdbDependencies>(
                api1 = CommonUtilsComponentHolder.get()
            )

            NetworkTmdbComponentDependencyHolder { dependencyHolder, commonUtilsApi ->
                object : NetworkTmdbDependencies {
                    override val dependenciesHolder: BaseDependencyHolder<out FeatureDependencies>
                        get() = dependencyHolder
                }
            }.dependencies
        }

        DataMoviesComponentHolder.dependencyProvider = {
            class DataMoviesComponentDependencyHolder(
                override val block: (
                    BaseDependencyHolder<DataMoviesFeatureDependencies>,
                    CommonUtilsApi,
                    AppDbApi,
                    NetworkTmdbApi
                ) -> DataMoviesFeatureDependencies
            ) : DependencyHolder3<CommonUtilsApi, AppDbApi, NetworkTmdbApi, DataMoviesFeatureDependencies>(
                api1 = CommonUtilsComponentHolder.get(),
                api2 = AppDbComponentHolder.get(),
                api3 = NetworkTmdbComponentHolder.get()
            )

            DataMoviesComponentDependencyHolder { dependencyHolder, commonUtilsApi, appDbApi, networkTmdbApi ->
                object : DataMoviesFeatureDependencies {
                    override val dependenciesHolder: BaseDependencyHolder<out FeatureDependencies>
                        get() = dependencyHolder

                    override val transactionRunner: DatabaseTransactionRunner
                        get() = appDbApi.transactionRunner

                    override val entityInserter: EntityInserter
                        get() = appDbApi.entityInserter

                    override val moviesDao: MoviesDao
                        get() = appDbApi.moviesDao

                    override val popularMoviesDao: PopularMoviesDao
                        get() = appDbApi.popularMoviesDao

                    override val resourceProvider: ResourceProvider
                        get() = commonUtilsApi.resourceProvider

                    override val tmdbApi: TmdbApi
                        get() = networkTmdbApi.tmdbApi
                }
            }.dependencies
        }

        HomeFeatureComponentHolder.dependencyProvider = {
            class HomeFeatureComponentDependencyHolder(
                override val block: (BaseDependencyHolder<HomeFeatureDependencies>, CommonUtilsApi, DataMoviesFeatureApi) -> HomeFeatureDependencies
            ) : DependencyHolder2<CommonUtilsApi, DataMoviesFeatureApi, HomeFeatureDependencies>(
                api1 = CommonUtilsComponentHolder.get(),
                api2 = DataMoviesComponentHolder.get()
            )

            HomeFeatureComponentDependencyHolder { dependencyHolder, commonUtilsApi: CommonUtilsApi, dataMoviesApi: DataMoviesFeatureApi ->
                object : HomeFeatureDependencies {
                    override val dependenciesHolder: BaseDependencyHolder<out FeatureDependencies>
                        get() = dependencyHolder

                    override val context: Context = appContext

                    override val popularMoviesRepository: PopularMoviesRepository
                        get() = dataMoviesApi.popularMoviesRepository

//                    override val movieListRepository: MovieListRepository
//                        get() = dataMoviesApi.movieListRepository

                    override val moviesPagingRepository: MoviesPagingRepository
                        get() = dataMoviesApi.moviesPagingRepository
                }
            }.dependencies
        }


    }
}