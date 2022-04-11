package com.axkov.moviepick.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.axkov.moviepick.data.entities.MovieEntity
import io.reactivex.rxjava3.core.Maybe

@Dao
abstract class MoviesDao : EntityDao<MovieEntity>() {

    @Query("SELECT id FROM movies WHERE tmdb_id = :tmdbId")
    abstract fun getIdForTmdbId(tmdbId: Long): Maybe<Long>
}