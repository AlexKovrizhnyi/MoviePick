package com.axkov.moviepick.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axkov.moviepick.database.entities.MovieEntity
import com.axkov.moviepick.database.entities.PopularMovieEntry

@Database(
    entities = [
        MovieEntity::class,
        PopularMovieEntry::class
    ],
    version = 1,
    exportSchema = false
)
internal abstract class MoviePickRoomDatabase: RoomDatabase(), MoviePickDatabase {

    companion object {
        const val DATABASE_NAME = "MoviePick.db"
    }
}