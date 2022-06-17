package com.axkov.moviepick.database.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.axkov.moviepick.core.di.annotations.AppScope
import com.axkov.moviepick.database.MoviePickDatabase
import com.axkov.moviepick.database.MoviePickRoomDatabase
import com.axkov.moviepick.database.utils.DatabaseTransactionRunner
import com.axkov.moviepick.database.utils.EntityInserter
import com.axkov.moviepick.database.utils.MoviePickEntityInserter
import com.axkov.moviepick.database.utils.RoomTransactionRunner
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        RoomDatabaseModule::class,
        DatabaseDaoModule::class,
        DatabaseModuleBinds::class
    ]
)
internal interface DatabaseModule

@Module
internal class RoomDatabaseModule {
    @AppScope
    @Provides
    fun provideDatabase(context: Context): MoviePickRoomDatabase {
        val builder = Room.databaseBuilder(
            context,
            MoviePickRoomDatabase::class.java,
            MoviePickRoomDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()

        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }
}

@Module
internal class DatabaseDaoModule {

    @Provides
    fun providePopularMoviesDao(db: MoviePickRoomDatabase) = db.popularMoviesDao()

    @Provides
    fun provideMoviesDao(db: MoviePickRoomDatabase) = db.moviesDao()
}

@Module
internal abstract class DatabaseModuleBinds {
    @Binds
    abstract fun bindMoviePickDatabase(db: MoviePickRoomDatabase): MoviePickDatabase

    @AppScope
    @Binds
    abstract fun bindEntityInserter(inserter: MoviePickEntityInserter): EntityInserter

    @AppScope
    @Binds
    abstract fun bindTransactionRunner(runner: RoomTransactionRunner): DatabaseTransactionRunner
}