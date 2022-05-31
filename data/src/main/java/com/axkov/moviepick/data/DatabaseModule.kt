package com.axkov.moviepick.data

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.axkov.moviepick.core.di.annotations.FeatureScope
import com.axkov.moviepick.data.utils.DatabaseTransactionRunner
import com.axkov.moviepick.data.utils.EntityInserter
import com.axkov.moviepick.data.utils.MoviePickEntityInserter
import com.axkov.moviepick.data.utils.RoomTransactionRunner
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [
    RoomDatabaseModule::class,
    DatabaseDaoModule::class,
    DatabaseModuleBinds::class
])
interface DatabaseModule

@Module
class RoomDatabaseModule {
    @FeatureScope
    @Provides
    fun provideDatabase(context: Context): MoviePickRoomDatabase {
        val builder = Room.databaseBuilder(context, MoviePickRoomDatabase::class.java, "movies.db")
            .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }
}

@Module
class DatabaseDaoModule {

    @Provides
    fun providePopularMoviesDao(db: MoviePickRoomDatabase) = db.popularMoviesDao()

    @Provides
    fun provideMoviesDao(db: MoviePickRoomDatabase) = db.moviesDao()
}

@Module
abstract class DatabaseModuleBinds {
    @Binds
    abstract fun bindMoviePickDatabase(db: MoviePickRoomDatabase): MoviePickDatabase

//    @Singleton // TODO: Change DatabaseModule connection from HomeComponent to AppComponent and then
    // change scope
    @FeatureScope
    @Binds
    abstract fun bindEntityInserter(inserter: MoviePickEntityInserter): EntityInserter

    @FeatureScope
    @Binds
    abstract fun bindTransactionRunner(runner: RoomTransactionRunner): DatabaseTransactionRunner
}