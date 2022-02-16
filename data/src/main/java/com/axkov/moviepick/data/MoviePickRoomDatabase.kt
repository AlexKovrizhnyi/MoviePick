package com.axkov.moviepick.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axkov.moviepick.data.daos.MoviesDao
import com.axkov.moviepick.data.entities.MovieEntity
import com.axkov.moviepick.data.entities.PopularMovieEntry

@Database(
    entities = [
        MovieEntity::class,
        PopularMovieEntry::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MoviePickRoomDatabase: RoomDatabase(), MoviePickDatabase